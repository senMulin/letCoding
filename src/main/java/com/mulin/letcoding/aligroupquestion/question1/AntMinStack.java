package com.mulin.letcoding.aligroupquestion.question1;


/**
 * Created by
 * @author mulin
 * @date 2018/8/27
 * @description:
 *
 * 设计含最小函数min()、pop()、push()的栈AntMinStack
 * 要求：
 * 1.AntMinStack实现测试，满足栈特性
 * 2.要求min、push、pop、的时间复杂度都是O(1)
 * 思路: 用两个栈来存储，一个栈存正常的出栈入栈元素，另一个存储出现过的最小元素
 * 入栈时比较是否小于最小栈栈顶，是则压入最小栈
 * 出站时比较是否等于最小栈栈顶，是则从最小栈出栈
 */
public class AntMinStack<E extends Comparable<E>> implements Stack<E> {

    /**
     * 记录正常 push, pop 栈节点
     */
    private final Stack stack = new LinkedStack();

    /**
     * 记录已出现过的最小节点
     */
    private final Stack minStack = new LinkedStack();

    @Override
    public E pop() {
        E e = (E) stack.pop();

        //比较已出现的最小元素栈的栈顶元素,相同则弹出
        if (null != e && e.equals(min())) {
            minStack.pop();
        }

        return e;
    }

    @Override
    public E push(E item) {
        E e = (E) stack.push(item);

        E min;
        //比较已出现的最小元素栈的栈顶元素, 小于原来的元素则入栈
        if (null == (min = min()) || e.compareTo(min) <= 0) {
            minStack.push(item);
        }

        return e;
    }

    @Override
    public E top() {
        E e = (E) stack.top();
        return e;
    }

    public E min() {
        E e = (E) minStack.top();
        return e;
    }
}

class LinkedStack<E> implements Stack<E> {

    public Node<E> top;

    static class Node<E> {
        public E item;
        public Node next;

        public Node(E e) {
            this.item = e;
        }

        public Node(E e, Node next) {
            this.item = e;
            this.next = next;
        }
    }

    @Override
    public E pop() {
        if (null == top) {
            return null;
        } else {
            E item = top.item;
            top = top.next;
            return item;
        }
    }

    @Override
    public E push(E item) {

        if (null == top) {
            top = new Node(item);
        } else {
            top = new Node<E>(item, top);
        }

        return item;
    }

    @Override
    public E top() {
        if (null == top) {
            return null;
        } else {
            E item = top.item;
            return item;
        }
    }
}