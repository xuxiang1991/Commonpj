package com.xuxiang.pj.network.config;

import android.content.Context;

import com.xuxiang.pj.view.ShowTipDialog;


/**
 * 网络弹出框工具类
 * Created by zhangwei on 2017/1/16.
 */

public class DailogUtil {
    private static ShowTipDialog mShowTipDialog; //网络请求弹出对话框

    public static void showNetDialog(Context context) {
        if (mShowTipDialog == null) {
            mShowTipDialog = new ShowTipDialog();
            mShowTipDialog.showDialog(context);
        }
    }

    public static void closeNetDialog() {
        if (mShowTipDialog != null) {
            mShowTipDialog.closeDialog();
            mShowTipDialog = null;
        }
    }
}
