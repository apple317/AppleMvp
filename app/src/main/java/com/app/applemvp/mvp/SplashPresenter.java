package com.app.applemvp.mvp;


import com.base.applemvp.common.BasePresenter;



/**
 * applehsp  on 2018/5/17.
 */

public class SplashPresenter extends BasePresenter<SplashView> {


    public SplashPresenter() {
    }




    /**
     * 广告方法
     * @param message
     */
    public void advertList(String message) {
        getView().advertList(message);
    }




}
