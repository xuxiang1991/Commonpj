package com.xuxiang.pj.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;
import com.xuxiang.pj.entity.eventbus.MessageEvent;
import com.xuxiang.pj.entity.eventbus.ToastMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * 类名称：登陆页
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class LoginActivity extends BaseToolbarActivity{
    private Button bt_go;
    private TextView tv_message;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupViews() {

        bt_go=(Button)findViewById(R.id.bt_go);
        tv_message=(TextView)findViewById(R.id.tv_message);


        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("你真好"));
               finish();
            }
        });

        bt_go.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                EventBus.getDefault().post(new ToastMessage("6666666"));
                finish();
                return false;
            }
        });
    }

    @Override
    protected void initialized() {

    }
}
