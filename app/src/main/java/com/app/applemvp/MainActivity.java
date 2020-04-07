package com.app.applemvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.applemvp.mvp.MainPresenter;
import com.app.applemvp.mvp.SplashPresenter;
import com.app.applemvp.mvp.SplashView;
import com.base.applemvp.annotations.CreatePresenterAnnotation;
import com.base.applemvp.common.BaseMvpActivity;
import com.app.applemvp.mvp.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseMvpActivity implements SplashView, MainView{


    @BindView(R.id.btnSync)
    Button btnSync;
    @BindView(R.id.btnAdvert)
    Button btnAdvert;


    @CreatePresenterAnnotation(SplashPresenter.class)
    SplashPresenter splashPresenter;

    @CreatePresenterAnnotation(MainPresenter.class)
    MainPresenter mainPresenter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public void appSync(String msg) {
        btnSync.setText(msg);
    }

    @Override
    public void advertList(String advert) {
        btnAdvert.setText(advert);
    }


    @OnClick({R.id.btnSync, R.id.btnAdvert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSync:
                mainPresenter.appSync("首页初始化");
                break;
            case R.id.btnAdvert:
                splashPresenter.advertList("启动页初始化");
                break;
        }
    }
}
