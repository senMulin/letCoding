package com.mulin.test;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/10/11
 * @description:
 */
public class Parent {

    protected  Integer a;

    public Integer getA() {
        return a;
    }

    public static class Child extends Parent {
        Integer a = 1;

        @Override
        public Integer getA() {
            return a;
        }
    }

    public static void main(String[] args) {
        Parent p =  new Child();

        System.out.println(p.getA());

    }
}
