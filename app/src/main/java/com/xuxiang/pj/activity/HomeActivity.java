package com.xuxiang.pj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseToolbarActivity;

/**
 * 类名称：主页
 * 类描述：
 * 创建人：xuxiang
 * 修改人：
 */
public class HomeActivity extends BaseToolbarActivity implements View.OnClickListener {


    //设置抽屉DrawerLayout
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar mToolbar;


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void initialized() {
        //设置ToolBar，具体可以参考另一篇文章：http://blog.csdn.net/qq_22078107/article/details/53327181
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar);
        try {
            //使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //隐藏toolbar上的app title
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
            //先启用home as up：即ToolBar左边那个打开侧边栏那个按钮（不开启点击按钮无反应的）
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        //设置抽屉DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//channge the icon,改变图标
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.red));//设置状


        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_camera:
                        //打开Fragment
                        //           getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentOne()).commit();
                        //           mToolbar.setTitle("帮助");
                        Toast.makeText(self, "帮助~", Toast.LENGTH_LONG).show();
                        mToolbar.setTitle("帮助");


                        break;

                    case R.id.nav_slideshow:
                        //打开Fragment
                        //           getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentTwo()).commit();
                        //           mToolbar.setTitle("收藏");
                        Toast.makeText(self, "收藏~", Toast.LENGTH_LONG).show();
                        mToolbar.setTitle("收藏");
                        break;

                    case R.id.nav_share:
                        //打开Fragment
                        //           getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentThree()).commit();
                        //           mToolbar.setTitle("关于我们");
                        Toast.makeText(self, "关于我们~", Toast.LENGTH_LONG).show();
                        mToolbar.setTitle("关于我们");
                        break;
                }

                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });


        //为头布局headerLayout上的控件添加监听事件；
        //先获得头部的View，再findViewByid()即可;如：
        View headerView = mNavigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.id_name);

    }


//    /**
//     * 开启toolbal最右边 三个点 那个“更多”按钮
//     * （不写此方法的话那按钮不显示）
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_nav, menu);
//        return true;
//    }


//    /**
//     * 打开侧滑栏（toolbar左边那个按钮）
//     * （在home菜单被点击的时候，打开drawer；不写此方法点击那按钮会没反应）
//     * @param item
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

}
