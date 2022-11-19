package com.mashibing.tank.test;

public class Student extends Person{

    {
        System.out.println("子类的非静态块");
    }
    static {
        System.out.println("子类的静态块，我是一个学生，我也属于人类");
    }

    private Student() {
        System.out.println("子类的构造方法，init student");
        hello("学生");
    }

    /*@Override
    public void hello(String name) {
        System.out.println("我是一个学生");
    }*/

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("初始化完成子类");
        student.hello("datr");
    }
}
