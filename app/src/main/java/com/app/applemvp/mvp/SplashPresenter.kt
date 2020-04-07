package com.app.applemvp.mvp

import com.base.applemvp.common.BasePresenter

/**
 * applehsp  on 2018/5/17.
 */
class SplashPresenter : BasePresenter<SplashView>() {
    /**
     * 广告方法
     * @param message
     */
    fun advertList(message: String) {
        view!!.advertList(message)
    }
}