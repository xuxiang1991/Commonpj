package com.xuxiang.pj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;
import com.xuxiang.pj.entity.eventbus.MessageEvent;
import com.xuxiang.pj.entity.eventbus.ToastMessage;
import com.xuxiang.pj.fragment.BaseFragment;
import com.xuxiang.pj.fragment.Frag;
import com.xuxiang.pj.fragment.LoginFragment;
import com.xuxiang.pj.fragment.RegisterFragment;
import com.xuxiang.pj.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

/**
 * 类名称：登陆页
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class LoginActivity extends BaseToolbarActivity implements View.OnClickListener,BaseFragment.ActivityBack{
    private ImageView ivLogo;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private Fragment loginFrg, registFrg;
    private MyPagerAdapter mdapter;

    private LinearLayout ll_login_register, ll_forget_secret;
    private ImageView iv_drop;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupViews() {


        ll_login_register = findViewById(R.id.ll_login_register);
        ll_forget_secret = findViewById(R.id.ll_forget_secret);
        iv_drop = findViewById(R.id.iv_drop);
        iv_drop.setOnClickListener(this);
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        viewpager.setOffscreenPageLimit(2);
        mdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mdapter);
        viewpager.setCurrentItem(0);

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.shape_tab_devider));
        linearLayout.setDividerPadding((int) Utils.dp2px(getResources(), 22));
        tabLayout.setupWithViewPager(viewpager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                Utils.setIndicator(tabLayout, 16, 16);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void initialized() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_drop:
                ll_forget_secret.setVisibility(View.GONE);
                ll_login_register.setVisibility(View.VISIBLE);

//                startActivity(new Intent(self, SplashActivity.class));
                break;
        }

    }

    @Override
    public void onBack(int flag) {
        if (flag == 0) {
                ll_forget_secret.setVisibility(View.VISIBLE);
                ll_login_register.setVisibility(View.GONE);
            }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private String Title[] = {"登陆", "注册"};


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();
//            bundle.putSerializable("interface", activityBack);

            if (position == 0) {
                if (loginFrg == null) {
                    loginFrg = new LoginFragment();
                    loginFrg.setArguments(bundle);
                }
                return loginFrg;
            } else {
                if (registFrg == null) {
                    registFrg = new RegisterFragment();
                    registFrg.setArguments(bundle);
                }
                return registFrg;
            }

        }

        @Override
        public int getCount() {
            return Title.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return Title[position];
        }
    }


}
