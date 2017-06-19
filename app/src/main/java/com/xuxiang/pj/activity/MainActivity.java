package com.xuxiang.pj.activity;

import android.app.Activity;

import com.afollestad.materialdialogs.util.DialogUtils;
import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;
import com.xuxiang.pj.network.config.DailogUtil;
import com.xuxiang.pj.network.config.DomainUrl;
import com.xuxiang.pj.network.service.CommonApiProvider;
import com.xuxiang.pj.network.service.CommonRequest;
import com.xuxiang.pj.network.service.CommonResponse;
import com.xuxiang.pj.view.ShowTipDialog;

/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MainActivity extends BaseToolbarActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void initialized() {
        DailogUtil.showNetDialog(self);
        CommonApiProvider.getNetGetCommon(DomainUrl.UPLOAD_DATA,new CommonResponse<String>(){
            @Override
            public void onSuccess(CommonRequest request, String data) {
                super.onSuccess(request, data);
                errorLog(data+"");
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                super.onFail(errorCode, errorMsg);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                DailogUtil.closeNetDialog();
            }
        });

    }
}
