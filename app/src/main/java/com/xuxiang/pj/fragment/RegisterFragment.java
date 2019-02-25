package com.xuxiang.pj.fragment;

import android.view.View;

import com.xuxiang.pj.R;

/**
 * 类名称：注册页面
 * 类描述：
 * 创建人：xuxiang
 * 修改人：
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener{
//    private ActivityBack activityBack;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void setupViews(View view) {

    }

    @Override
    protected void initialized() {
//        activityBack = (ActivityBack)getArguments().getSerializable( "interface");
    }

    @Override
    protected void lazyLoad() {

    }
}
