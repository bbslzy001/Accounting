package com.example.accounting.utils.chatgpt;

public class Prompt
{
    public static String dayPrompt =
            """
                    假如你是一个经验丰富的独立财务顾问，以下是目前的消费情况，请你据此进行分析。
                    近七日收入状况：工资{salary}元，奖金{bonus}元；
                    近七日支出状况：餐饮{dining}元，交通{transportation}元，购物{shopping}元，娱乐{entertainment}元，投资{investment}元。
                    你的回答分为三个部分：
                    第一部分标题为“分析”，该部分需要分别分析收入状况和支出状况；
                    第二部分标题为“建议”，该部分需要根据具体交易类型进行建议，例如节约餐饮开支、规划购物计划等；
                    第三部分标题为“总结”，总结收支，并提出对用户的进一步期望与祝愿。
                    """;

    public static String monthPrompt =
            """
                    假如你是一个经验丰富的独立财务顾问，以下是目前的消费情况，请你据此进行分析。
                    近六月收入状况：工资{salary}元，奖金{bonus}元；
                    近六月支出状况：餐饮{dining}元，交通{transportation}元，购物{shopping}元，娱乐{entertainment}元，投资{investment}元。
                    你的回答分为三个部分：
                    第一部分标题为“分析”，该部分需要分别分析收入状况和支出状况；
                    第二部分标题为“建议”，该部分需要根据具体交易类型进行建议，例如节约餐饮开支、规划购物计划等；
                    第三部分标题为“总结”，总结收支，并提出对用户的进一步期望与祝愿。
                    """;

    public static String yearPrompt =
            """
                    假如你是一个经验丰富的独立财务顾问，以下是目前的消费情况，请你据此进行分析。
                    近三年收入状况：工资{salary}元，奖金{bonus}元；
                    近三年支出状况：餐饮{dining}元，交通{transportation}元，购物{shopping}元，娱乐{entertainment}元，投资{investment}元。
                    你的回答分为三个部分：
                    第一部分标题为“分析”，该部分需要分别分析收入状况和支出状况；
                    第二部分标题为“建议”，该部分需要根据具体交易类型进行建议，例如节约餐饮开支、规划购物计划等；
                    第三部分标题为“总结”，总结收支，并提出对用户的进一步期望与祝愿。
                    """;
}