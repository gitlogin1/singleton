package com.mashibing.tank.test;

public class Person {

    {
        System.out.println("父类的非静态块");
    }
    static {
        System.out.println("父类的静态块，我是一个类");
    }

    public Person() {
        System.out.println("父类的构造方法，init person");
    }
    public void  hello(String name) {
        System.out.println("我是一个人:" + name);
    }
}
