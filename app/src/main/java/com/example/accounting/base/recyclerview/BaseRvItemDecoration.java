package com.example.accounting.base.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;

import java.util.Objects;

public abstract class BaseRvItemDecoration<A extends BaseRvAdapter, G extends BaseRvGroup> extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener
{
    protected final A adapter;
    protected final Context context;
    protected HeaderCoordinate headerCoordinate;
    protected G currentGroup;
    protected View currentHeaderView;

    public BaseRvItemDecoration(Context context, A adapter)
    {
        this.context = context;
        this.adapter = adapter;
    }

    /**
     * 创建一个新的悬浮列表头
     */
    protected abstract View createHeaderView(RecyclerView recyclerView, G group);

    /**
     * 初始化 HeaderInfo
     */
    private void initHeaderCoordinate(RecyclerView recyclerView, int firstVisiblePosition)
    {
        int firstVisibleViewType = adapter.getItemViewType(firstVisiblePosition); // 获取第一个可见的 item 的类型
        if (firstVisibleViewType == BaseRvAdapter.HEADER_ITEM)  // 如果是列表头
        {
            headerCoordinate = new HeaderCoordinate(recyclerView.getChildAt(firstVisiblePosition));
        }
    }

    /**
     * 获取第一个可见item的位置
     */
    private int getFirstVisiblePosition(RecyclerView recyclerView)
    {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return Objects.requireNonNull(layoutManager).findFirstVisibleItemPosition();
    }

    /**
     * 当 recyclerview 存在数据的时候，程序启动一定先执行 onDrawOver() 再执行 onInterceptTouchEvent()
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
        if (state.isMeasuring() || adapter.getItemCount() == 0)  // 如果 recyclerview 正在进行布局前的准备工作或数据为空
        {
            return; // 直接返回，不做任何操作
        }

        int firstVisiblePosition = getFirstVisiblePosition(parent);  // 获取第一个可见的 item 的位置
        if (firstVisiblePosition == -1) return;  // 如果LayoutManager还没有完全布局，则直接返回，不做任何操作

        if (headerCoordinate == null)
            initHeaderCoordinate(parent, firstVisiblePosition);  // 初始化 headerInfo

        int groupIndex = adapter.getGroupIndex(firstVisiblePosition);  // 获取所有 view 对应的 group 下标
        G group = (G) adapter.getGroupList().get(groupIndex); // 获取当前组对象

        if (currentGroup == null || currentGroup != group)
        {
            currentGroup = group;
            currentHeaderView = createHeaderView(parent, group);  // 初始化一个新的列表头
        }

        drawFloatingHeader(c);
    }

    /**
     * 将 HeaderView 绘制到 RecyclerView 的画布上
     */
    private void drawFloatingHeader(Canvas canvas)
    {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(headerCoordinate.headerRight - headerCoordinate.headerLeft, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(headerCoordinate.headerBottom - headerCoordinate.headerTop, View.MeasureSpec.EXACTLY);
        currentHeaderView.measure(widthSpec, heightSpec);
        currentHeaderView.layout(headerCoordinate.headerLeft, headerCoordinate.headerTop, headerCoordinate.headerRight, headerCoordinate.headerBottom);
        currentHeaderView.draw(canvas);
    }

    /**
     * 判断是否点击了悬浮列表头
     *
     * @param x      获取触摸事件的坐标
     * @param y      获取触摸事件的坐标
     * @param action 获取触摸事件的动作
     */
    private boolean isFloatingHeaderClicked(float x, float y, int action)
    {
        if (action != MotionEvent.ACTION_DOWN) return false;
        return x >= headerCoordinate.headerLeft && x <= headerCoordinate.headerRight && y >= headerCoordinate.headerTop && y <= headerCoordinate.headerBottom;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e)
    {
        if (adapter.getItemCount() == 0) return false;  // 如果 recyclerView 数据为空

        // 参数 0 表示获取第一个触摸点的坐标，以避免数组越界异常
        // 注意：下面的代码仅适用于判断单点触摸事件
        if (!isFloatingHeaderClicked(e.getX(0), e.getY(0), e.getAction())) return false;

        // 将 recyclerview 滚动至列表头原位置
        int firstVisiblePosition = getFirstVisiblePosition(rv);
        int subItemIndex = adapter.getSubItemIndex(firstVisiblePosition);
        int headerItemIndex = firstVisiblePosition - subItemIndex - 1;
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        if (layoutManager != null) layoutManager.scrollToPositionWithOffset(headerItemIndex, 0);

        // 悬浮列表头点击事件
        adapter.onHeaderClick(currentGroup, headerItemIndex);

        // 更新悬浮列表头的图标显示
        ImageView imageView = currentHeaderView.findViewById(R.id.header_toggle);
        imageView.setImageResource(currentGroup.isExpanded() ? R.drawable.ic_expand_less : R.drawable.ic_expand_more);

        return true;  // 拦截触摸事件，不再传递给子 view
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e)
    {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {
    }

    private static class HeaderCoordinate
    {
        private final int headerTop;
        private final int headerBottom;
        private final int headerLeft;
        private final int headerRight;

        public HeaderCoordinate(View headerView)
        {
            this.headerTop = headerView.getTop();
            this.headerBottom = headerView.getBottom();
            this.headerLeft = headerView.getLeft();
            this.headerRight = headerView.getRight();
        }
    }
}