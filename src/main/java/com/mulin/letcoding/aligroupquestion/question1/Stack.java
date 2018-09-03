package com.mulin.letcoding.aligroupquestion.question1;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/27
 * @description:
 * Stack 接口
 */
public interface Stack<E> {
    /**
     * 弹出栈顶元素
     * @return
     */
    E pop();

    /**
     * 推入元素进栈并置为栈顶元素
     * @param item
     * @return
     */
    E push(E item);

    /**
     * 返回栈顶元素不删除
     * @return
     */
    E top();
}
