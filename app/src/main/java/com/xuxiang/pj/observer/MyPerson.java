package com.xuxiang.pj.observer;

import java.util.Observable;

/**
 * 类名称：被观察者
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MyPerson extends Observable {

    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        setChanged();
        notifyObservers();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "MyPerson [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
}