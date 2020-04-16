package com.base.applemvp.common

import android.os.Bundle
import android.util.Log
import com.base.applemvp.factroy.MvpPresenterFactroyImpl
import com.base.applemvp.factroy.PresenterProxyFactroyImpl
import com.trello.rxlifecycle3.LifecycleTransformer

/**
 * @author applehsp
 */
abstract class BaseMvpActivity : BaseActivity(), IBaseView {
    /**
     * 绑定生命周期
     * @return
     */
    override fun <T> bindLifeycle(loading: Boolean): LifecycleTransformer<T> {
        showProgress(loading)
        Log.e("HU", "bindLifeycle====");
        return bindToLifecycle()
    }
    val mProxy=PresenterProxyFactroyImpl( MvpPresenterFactroyImpl.mvpCreate(this))
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        mProxy.onCreate(this)
        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        Log.e("perfect-mvp", "V onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        mProxy.onDestroy()
        /**
         * 销毁Presenter
         */
        Log.e("perfect-mvp", "V onDestroy = ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("perfect-mvp", "V onSaveInstanceState")
    }


    override fun showProgress(loading: Boolean) {}


}