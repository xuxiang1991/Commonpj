package com.xuxiang.pj.observer;

import com.xuxiang.pj.utils.Logger;

import java.util.Observable;
import java.util.Observer;

/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MyObserver implements Observer {

    private int id;
    private MyPerson myPerson;

    public MyObserver(int id) {
        System.out.println("我是观察者---->" + id);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyPerson getMyPerson() {
        return myPerson;
    }

    public void setMyPerson(MyPerson myPerson) {
        this.myPerson = myPerson;
    }

    @Override
    public void update(Observable observable, Object data) {
        Logger.e("xx_observer", "观察者---->" + id + "得到更新：");
        this.myPerson = (MyPerson) observable;
        Logger.e("xx_observer", ((MyPerson) observable).toString());
    }

}