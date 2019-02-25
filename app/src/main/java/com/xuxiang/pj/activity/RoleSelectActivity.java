package com.xuxiang.pj.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;


/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class RoleSelectActivity extends BaseToolbarActivity implements View.OnClickListener {

    private TextView tv_go;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go:
                startActivity(new Intent(self, LoginActivity.class));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.actitivty_role_select;
    }

    @Override
    protected void setupViews() {
        tv_go = findViewById(R.id.tv_go);
        tv_go.setOnClickListener(this);
    }

    @Override
    protected void initialized() {

    }
}
