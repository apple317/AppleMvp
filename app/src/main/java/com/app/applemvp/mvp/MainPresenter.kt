package com.app.applemvp.mvp

import android.util.Log
import com.base.applemvp.common.BasePresenter

/**
 * applehsp  on 2018/5/17.
 */
class MainPresenter : BasePresenter<MainView>() {
    /**
     * 初始化
     */
    fun appSync(message: String) {
        view!!.appSync("xxxxdddddd")
    }
}