package com.example.create.bean4;

/**
 * @author dmrfcoder
 * @date 2019/2/14
 */

public class DemoBean {
    private String name;
    private int age;
    private boolean boy;

    public DemoBean(String name, int age, boolean boy) {
        this.name = name;
        this.age = age;
        this.boy = boy;
    }

    @Override
    public String toString() {
        return "DemoBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boy=" + boy +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoy() {
        return boy;
    }

    public void setBoy(boolean boy) {
        this.boy = boy;
    }
}