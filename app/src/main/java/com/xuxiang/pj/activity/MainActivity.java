package com.xuxiang.pj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;
import com.xuxiang.pj.entity.eventbus.MessageEvent;
import com.xuxiang.pj.entity.eventbus.ToastMessage;
import com.xuxiang.pj.network.config.DailogUtil;
import com.xuxiang.pj.network.config.DomainUrl;
import com.xuxiang.pj.network.service.CommonApiProvider;
import com.xuxiang.pj.network.service.CommonRequest;
import com.xuxiang.pj.network.service.CommonResponse;
import com.xuxiang.pj.observer.MyObserver;
import com.xuxiang.pj.observer.MyPerson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MainActivity extends BaseToolbarActivity implements View.OnClickListener {

    Button btGo;
    TextView tvMessage;

    int i = 0;
    MyPerson person;
    String[] names = new String[]{"张三", "李四", "王五"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupViews() {

        btGo = findViewById(R.id.bt_go);
        tvMessage = findViewById(R.id.tv_message);

        EventBus.getDefault().register(self);
    }

    @Override
    protected void initialized() {
        DailogUtil.showNetDialog(self);
        CommonApiProvider.getNetGetCommon(DomainUrl.UPLOAD_DATA, new CommonResponse<String>() {
            @Override
            public void onSuccess(CommonRequest request, String data) {
                super.onSuccess(request, data);
                errorLog(data + "");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        tvMessage.setText(event.getMessage());
// EventBus.getDefault().post(new MessageEvent("你真好"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onToastEvent(ToastMessage event) {
        ShowToast(event.getMessage());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_go:
                break;
        }

    }
}
