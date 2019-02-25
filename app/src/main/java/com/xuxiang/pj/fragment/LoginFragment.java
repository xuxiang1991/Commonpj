package com.xuxiang.pj.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.HomeActivity;
import com.xuxiang.pj.activity.SplashActivity;

import java.io.Serializable;

/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private ActivityBack activityBack;
    private Button bt_login;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
//                activityBack.onBack(0);
                getActivity().startActivity(new Intent(self, HomeActivity.class));
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setupViews(View view) {
        bt_login = view.findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

    }

    @Override
    protected void initialized() {
        if(self instanceof ActivityBack) {
            activityBack = (ActivityBack)self; // 2.2 获取到宿主activity并赋值
        } else{
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }

    @Override
    protected void lazyLoad() {

    }
}
