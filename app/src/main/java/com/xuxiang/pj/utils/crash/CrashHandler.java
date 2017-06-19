package com.xuxiang.pj.utils.crash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;

import com.xuxiang.pj.utils.ActivityManager;
import com.xuxiang.pj.utils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 */
public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    // CrashHandler 实例
    private static CrashHandler INSTANCE = new CrashHandler();

    // 程序的 Context 对象
    private Context mContext;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);


    /**
     * 保证只有一个 CrashHandler 实例
     */
    private CrashHandler() {
    }

    /**
     * 获取 CrashHandler 实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;

        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(TAG, "error:", ex);

        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Logger.e(TAG, "error : ", e);
            }

            // 退出程序
            int currentVersion = android.os.Build.VERSION.SDK_INT;
            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
//            	Intent startMain = new Intent(Intent.ACTION_MAIN);
//                startMain.addCategory(Intent.CATEGORY_HOME);
//                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(startMain);

                //重启APP
//    			restartApplication();

                ActivityManager.getScreenManager().ExitAll();
            } else {// android2.1
                ActivityManager.getScreenManager().ExitAll();
                System.exit(0);
            }

        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 使用 Toast 来显示异常信息
//		new Thread() {
//			@Override
//			public void run() {
//				Looper.prepare();
//				try {
//				    Toast.makeText(mContext, R.string.crashhandler_1, Toast.LENGTH_LONG).show();
//				} catch (NotFoundException e) {
//
//				}
//
//				Looper.loop();
//			}
//		}.start();

        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
        //上传错误日志
//        saveCrashInfo2Site(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Logger.e(TAG, "an error occured when collect package info", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Logger.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Logger.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     * *
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }

            return fileName;
        } catch (Exception e) {
            Logger.e(TAG, "an error occured while writing file...", e);
        }

        return null;
    }

    /**
     * 重启程序
     */
    private void restartApplication() {
        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

//    /**
//     * 保存错误信息到主站
//     * *
//     *
//     * @param ex
//     * @return 返回接口输出
//     */
//    private void saveCrashInfo2Site(Throwable ex) {
//        // TODO Auto-generated method stub
//        StringBuffer sb = new StringBuffer();
//        for (Map.Entry<String, String> entry : infos.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            sb.append(key + "=" + value + "\n");
//        }
//
//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
//        Throwable cause = ex.getCause();
//        while (cause != null) {
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//        printWriter.close();
//
//        String result = writer.toString();
//        sb.append(result);
//
//        JSONObject params = new JSONObject();
//        try {
//            String userid = ApplicationData.getInstance().getUserName();
//            if ("".equals(userid))
//                userid = "0";
//            params.put("platformversion", android.os.Build.VERSION.RELEASE);
//            params.put("platformcode", android.os.Build.VERSION.SDK);
//            params.put("appversion", getVersionName());
//            params.put("userid", userid);
//            params.put("uuid", CommonApiProvider.Uuid);
//            params.put("mobilemodel", android.os.Build.MODEL);
//            params.put("description", sb.toString());
//        } catch (JSONException ex1) {
//            Logger.e("error", ex1.toString());
//        }
//        AsyncLoader(params.toString());
//    }
//
//
//    /**
//     * 添加崩溃记录
//     *
//     * @param param
//     */
//    private void AsyncLoader(String param) {
//        CommonApiProvider.getNetPostCommon(DomainUrl.SITE_PATH, Action.ADD_CRASH_DATA, param.toString(), new CommonResponse<String>() {
//            @Override
//            public void onSuccess(CommonRequest request, String data) {
//                super.onSuccess(request, data);
//            }
//
//            @Override
//            public void onFail(int errorCode, String errorMsg) {
//                super.onFail(errorCode, errorMsg);
//            }
//
//            @Override
//            public void onComplete() {
//                super.onComplete();
//            }
//        });
//    }

//
//    private String getVersionName() {
//        String version = "unknow";
//        try {
//            PackageManager packageManager = mContext.getPackageManager();
//            PackageInfo packInfo;
//            packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
//            version = packInfo.versionName;
//        } catch (NameNotFoundException e) {
////			e.printStackTrace();
//        }
//        return version;
//    }
}
