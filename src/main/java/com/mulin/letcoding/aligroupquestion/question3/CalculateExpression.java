package com.mulin.letcoding.aligroupquestion.question3;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by
 * @author mulin
 * @date 2018/8/28
 * @description:
 * 设计数据结构与算法，计算算数表达式，需要支持基本计算，
 * 加减乘除，满足计算优先级例如输入 3*0+3+8+9*1 输出20。
 * 括号，支持括号，
 * 例如输入 3+（3-0）*2 输出 9假设所有的数字均为整数，
 * 无需考虑精度问题。
 */
public class CalculateExpression {
    private static final char LEFT_BRACKETS    = '(';
    private static final char RIGHT_BRACKETS   = ')';
    private static final char ASTERISK         = '*';
    private static final char SOLIDUS          = '/';
    private static final char PLUS             = '+';
    private static final char MINUS            = '-';

    /**
     * 操作符栈
     * 暂存操作符
     */
    Stack<Character> opPuctStack = new Stack();

    /**
     * 操作数栈
     * 暂存操作数和中间结果
     */
    Stack<Integer> opNumStack = new Stack();

    /**
     * 括弧栈，校验表达式
     */
    Stack<Character> bracketsStack = new Stack();


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();

        CalculateExpression calculateExpression = new CalculateExpression();

        try {
            Integer result = calculateExpression.traver(expression);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 遍历算数表达式
     *
     * @return
     */
    private Integer traver(String expre) throws Exception {


        //上个符号或完整数字, 只存在字符类型和字符串类型, 字符串类型只可能为数字字符串
        Object last = null;
        for (char cur : expre.toCharArray()) {

            switch (cur) {
                case LEFT_BRACKETS:
                    last = handleLBrackets(cur, last);
                    break;
                case RIGHT_BRACKETS:
                    last = handleRBrackets(cur, last);
                    break;

                case ASTERISK:
                case SOLIDUS:
                   last = handleSecLevelPunct(cur, last);
                    break;

                case PLUS:
                case MINUS:
                    last = handleFirstLevelPunct(cur, last);
                    break;

                default:
                    if (Character.isWhitespace(cur) || Character.isSpaceChar(cur)) {
                        continue;
                    } else if (Character.isDigit(cur) && (last instanceof String)) {
                        last = new StringBuilder()
                                .append(last)
                                .append(cur)
                                .toString();

                    } else if (Character.isDigit(cur) && !(last instanceof String)) {
                        last = new StringBuilder()
                        .append(cur)
                        .toString();
                    } else {
                        throw new IllegalStateException("invalid character: " + cur);
                    }

                    break;
            }
        }

        if (!(last instanceof Character && RIGHT_BRACKETS == (Character) last)) {
            checkLastNumAndPush(last);
        }


        Character lastOpPunct = null;
        while (!opPuctStack.isEmpty() && null != (lastOpPunct = opPuctStack.pop())) {
            Integer b = opNumStack.pop();
            Integer a = opNumStack.pop();

            Integer c = calculate(a, b, lastOpPunct);
            opNumStack.push(c);
        }

        if (!opPuctStack.isEmpty() || opNumStack.size() != 1) {
            throw new IllegalStateException("invalid expression");
        }

        return opNumStack.pop();
    }

    /**
     * 处理遍历到左括弧的情况
     * 1. 判断上一个对象是否为标点符号且不为右括弧,否,则抛出异常
     * 2. 操作符栈入栈
     * 3. 括弧栈入栈
     */
    private Character handleLBrackets(char cur, Object last) {
        if (!(last instanceof Character) || RIGHT_BRACKETS == (Character)last) {
            throw new IllegalStateException("invalid string before left brackets");
        }

        opPuctStack.push(cur);
        bracketsStack.push(cur);

        return cur;
    }

    /**
     * 处理遍历到右括弧的情况
     * 1. 判断上一个对象是否为数字字符串，否，抛出异常，是，将数字字符串压入操作数栈
     * 2. 括弧栈出栈，出栈元素不为做括弧时抛出异常
     * 3. 操作符栈出栈, 操作数栈出栈, 计算结果压入操作数栈
     */
    private Character handleRBrackets(char cur, Object last) {
        checkLastNumAndPush(last);

        Object lb = bracketsStack.pop();

        if (null == lb || !(lb instanceof Character) || LEFT_BRACKETS != (Character) lb) {
            new IllegalStateException("invalid expression");
        }

        Character lastOpPunct = null;

        while (!opPuctStack.isEmpty() && LEFT_BRACKETS != (lastOpPunct = opPuctStack.pop())) {
            Integer b = opNumStack.pop();
            Integer a = opNumStack.pop();

            Integer c = calculate(a, b, lastOpPunct);

            opNumStack.push(c);
        }

        return cur;
    }

    /**
     * 处理遍历到一级运算法的情况（+，-）
     * 1. 判断上一个对象是否为数字字符串或右括弧，否，抛出异常，是，数字字符串压入操作数栈，括弧不处理
     * 2. 操作符栈出栈, 操作数栈出栈, 计算结果压入操作数栈, 遇到左括弧或null跳出循环，这里一级运算符优先级最低
     * @param last
     * @param cur
     * @return
     */
    private Character handleFirstLevelPunct(char cur, Object last) {
        if (!(last instanceof Character && RIGHT_BRACKETS == (Character) last)) {
            checkLastNumAndPush(last);
        }

        Character lastOpPunct = null;

        while (!opPuctStack.isEmpty() &&
                null != (lastOpPunct = opPuctStack.pop()) &&
                LEFT_BRACKETS != lastOpPunct) {

            Integer b = opNumStack.pop();
            Integer a = opNumStack.pop();

            Integer c = calculate(a, b, lastOpPunct);

            opNumStack.push(c);
        }

        if (null != lastOpPunct && LEFT_BRACKETS == lastOpPunct) {
            opPuctStack.push(lastOpPunct);
        }

        opPuctStack.push(cur);
        return cur;
    }

    /**
     * 处理遍历到二级运算法的情况（*，/）
     * 1. 判断上一个对象是否为数字字符串或右括弧，否，抛出异常，是，数字字符串压入操作数栈，括弧不处理
     * 2. 操作符栈出栈, 操作数栈出栈, 计算结果压入操作数栈, 遇到左括弧或null或一级运算符跳出循环，这里二级运算符优先级高于一级运算符
     * @param last
     * @param cur
     * @return
     */
    private Character handleSecLevelPunct(char cur, Object last) {
        if (!(last instanceof Character && RIGHT_BRACKETS == (Character)last)) {
            checkLastNumAndPush(last);
        }

        Character lastOpPunct = null;

        while (!opPuctStack.isEmpty() &&
                null != (lastOpPunct = opPuctStack.pop()) &&
                LEFT_BRACKETS != lastOpPunct &&
                PLUS != lastOpPunct &&
                MINUS != lastOpPunct) {
            Integer b = opNumStack.pop();
            Integer a = opNumStack.pop();

            Integer c = calculate(a, b, lastOpPunct);
            opNumStack.push(c);
        }

        if (null != lastOpPunct &&
                (LEFT_BRACKETS == lastOpPunct ||
                PLUS == lastOpPunct ||
                MINUS == lastOpPunct)) {
            opPuctStack.push(lastOpPunct);
        }

        opPuctStack.push(cur);
        return cur;
    }

    /**
     * 校验上个数字字符串并压入操作数栈
     * @param last
     */
    private Object checkLastNumAndPush(Object last) {
        Integer lastNum = null;
        try {
            lastNum = Integer.parseInt(String.valueOf(last));
        }catch (NumberFormatException e) {
            throw new IllegalStateException("invalid expression");
        }
       return opNumStack.push(lastNum);
    }

    /**
     * 计算表达式结果
     * @param a
     * @param b
     * @param punct
     * @return
     */
    private Integer calculate(Integer a, Integer b, Character punct) {
        switch (punct) {
            case ASTERISK: return a * b;
            case SOLIDUS: return a / b;
            case PLUS: return a + b;
            case MINUS: return a - b;
            default:
                throw new IllegalStateException("invalid punctuation");
        }
    }
}
