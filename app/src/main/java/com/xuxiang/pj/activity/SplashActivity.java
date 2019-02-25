package com.xuxiang.pj.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.xuxiang.pj.R;
import com.xuxiang.pj.activity.base.BaseActivity;
import com.xuxiang.pj.utils.Config;
import com.xuxiang.pj.utils.Constant;

import java.io.File;


/**
 * 类名称：广告页
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class SplashActivity extends BaseActivity {

    ImageView ivLogo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setupViews() {
        ivLogo = findViewById(R.id.iv_logo);
    }

    @Override
    protected void initialized() {


        final File file = new File(Constant.mSplash_img_patch);
        if (file.exists()) {
            ivLogo.setImageURI(Uri.fromFile(file));
        } else {
            Config.setCurrentLOGO("");
            ivLogo.setImageResource(R.drawable.startup);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextPage();
            }
        }, 2000);

    }


    private void nextPage() {
        if (self.isFinishing()) {
            return;
        }
//        if (Config.isFirst()) {
//            createShortcut();
//        }
//        if (Config.getUserInfo() != null) {
        startActivity(new Intent(SplashActivity.this, RoleSelectActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        } else {
//            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        }

        finish();
    }


    /**
     * 创建桌面快捷方式
     */
    private void createShortcut() {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        // 不允许重复创建
        shortcut.putExtra("duplicate", false);
        // 指定快捷方式的启动对象
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(this.getPackageName(), this.getClass().getName());
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        // 发出广播
        sendBroadcast(shortcut);
        // 将第一次启动的标识设置为false
        Config.setFirst(false);
    }

}
