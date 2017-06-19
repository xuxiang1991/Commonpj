package com.xuxiang.pj;

import android.app.Application;

import com.xuxiang.pj.utils.crash.CrashHandler;

/**
 * 类名称：应用信息
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MyApplication extends Application {


    private static MyApplication singleInstance;

    public static MyApplication getInstance() {
        return singleInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        getApplicationContext();
        singleInstance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
