package com.app.applemvp.mvp;

import com.base.applemvp.common.BasePresenter;

/**
 * applehsp  on 2018/5/17.
 */

public class MainPresenter extends BasePresenter<MainView> {


    public MainPresenter() {
    }


    /**
     * 初始化
     */
    public void appSync(String message){
        getView().appSync(message);
    }




}
