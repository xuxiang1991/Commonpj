package com.xuxiang.pj.observer;

import java.util.Observable;

/**
 * 类名称：被观察者
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class DevTechFrontier extends Observable{

    public void postNewPublication(String content)
    {
        setChanged();
        notifyObservers(new Coder("新大兵报道"));

    }
}
