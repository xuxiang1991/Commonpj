package com.xuxiang.pj.observer;

import com.xuxiang.pj.utils.Logger;

import java.util.Observable;
import java.util.Observer;

/**
 * 类名称：观察者
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class Coder implements Observer {
    public String name;

    public Coder(String name) {
        this.name = name;
    }


    @Override
    public void update(Observable o, Object arg) {
        Logger.e("xx_observer", "HI:" + name + ",更新了内容：" + ((Coder)arg).toString()+ "--Oberverable--" + o);
    }

    @Override
    public String toString() {
        return "码农：" + name;
    }
}
