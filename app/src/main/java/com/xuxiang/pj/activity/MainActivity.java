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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名称：
 * 类描述：
 * 创建人：
 * 修改人：
 */
public class MainActivity extends BaseToolbarActivity  {

    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.tv_message)
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


        person = new MyPerson();

//        btGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                i++;
//                MyObserver observer = new MyObserver(i);
//                person.addObserver(observer);
//
//
////                DevTechFrontier devTechFrontier=new DevTechFrontier();
////                Coder mrsimple=new Coder("mr.simple");
////                Coder coder1=new Coder("code-1");
////                Coder coder2=new Coder("code-2");
////                Coder coder3=new Coder("code-3");
////
////                devTechFrontier.addObserver(mrsimple);
////                devTechFrontier.addObserver(coder1);
////                devTechFrontier.addObserver(coder2);
////                devTechFrontier.addObserver(coder3);
////
////                devTechFrontier.postNewPublication("新学期开始了");
//
////                startActivity(new Intent(self, LoginActivity.class));
//            }
//        });
        btGo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                person.setName(names[i % 3]);
                return true;
            }
        });
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

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onToastEvent(ToastMessage event) {
        ShowToast(event.getMessage());

    }


    @OnClick(R.id.bt_go)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_go:
                i++;
                MyObserver observer = new MyObserver(i);
                person.addObserver(observer);
                break;
        }

    }
}
