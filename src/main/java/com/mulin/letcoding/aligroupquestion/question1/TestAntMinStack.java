package com.mulin.letcoding.aligroupquestion.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by
 * @author mulin
 * @date 2018/8/27
 * @description:
 * AntMinStack 测试类
 * 以 Integer 为例
 */
public class TestAntMinStack {


    public static void main(String[] args) {
        int num = 10, bound = 50;

        AntMinStack<Integer> stack = new AntMinStack();

        List<Integer> list = randomList(num, bound);

        testPushInt(list, stack);

        testPopInt(list, stack);

        testMin(list, stack);

    }

    public static List<Integer> randomList(int num, int bound) {
        Random random = new Random();

        List<Integer> list = new ArrayList<Integer>(num);

        for (int i = 0; i < num; ++i) {
            list.add(random.nextInt(bound));
        }
        return list;
    }

    /**
     * 测试最小栈压入元素
     * 将 Integer 列表按序压入栈
     * @param list
     * @param stack
     */
    public static void testPushInt(List<Integer> list, AntMinStack<Integer> stack) {
        for (Integer i : list) {
            stack.push(i);

            System.out.printf("push item: %d\n", i);
        }
    }

    /**
     * 测试最小栈弹出元素
     * 比较最小栈弹出元素是否与
     * Integer 列表末尾相等
     * @param list
     * @param stack
     */
    public static void testPopInt(List<Integer> list, AntMinStack<Integer> stack) {
        Integer iStack = stack.pop();
        Integer iList = null;

        if (list.size() > 0) {
            iList = list.remove(list.size() - 1);
            assert iStack.equals(iList);
        }

        System.out.printf("stack top item: %d, list last item: %d\n", iStack, iList);
    }

    /**
     * 测试最小栈获取最小元素
     * 比较最小栈最小元素是否与
     *
     * @param list
     * @param stack
     */
    public static void testMin(List<Integer> list, AntMinStack<Integer> stack) {
        Integer iStack = stack.min();
        Integer iList = null;

        if (list.size() > 0) {
            iList = finMin(list);
            assert iStack.equals(iList);
        }

        System.out.printf("stack min item: %d, list min item: %d\n", iStack, iList);
    }

    private static Integer finMin(List<Integer> list) {
        Integer min = null;

        if (null != list && list.size() != 0) {
            for (Integer i : list) {
                if (null == min || i.compareTo(min) < 0) {
                    min = i;
                }
            }
        }

        return min;
    }
}

