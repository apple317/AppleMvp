package com.base.applemvp.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.applemvp.factroy.MvpPresenterFactroyImpl
import com.base.applemvp.factroy.PresenterProxyFactroyImpl
import com.trello.rxlifecycle3.LifecycleTransformer
import com.trello.rxlifecycle3.android.FragmentEvent

/**
 * @author applehsp
 */
abstract class BaseMvpFragment : BaseFragment(), IBaseView {
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
     var mProxy: PresenterProxyFactroyImpl = PresenterProxyFactroyImpl(MvpPresenterFactroyImpl.mvpCreate(this))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("HU", "onCreateView=BaseMvpFragment===$mProxy")
        mProxy.onCreate(this)
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY))
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.e("perfect-mvp", "V onResume")
        //        mProxy.onResume((V) this);
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("perfect-mvp", "V onDestroy = ")
        mProxy.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("perfect-mvp", "V onSaveInstanceState")
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState())
    }



    override fun <T> bindLifeycle(loading: Boolean): LifecycleTransformer<T> {
        showProgress(loading)
        return bindUntilEvent(FragmentEvent.DESTROY)
    }

    companion object {
        private const val PRESENTER_SAVE_KEY = "presenter_save_key"
    }
}