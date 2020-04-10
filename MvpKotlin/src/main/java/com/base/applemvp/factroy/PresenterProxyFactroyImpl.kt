package com.base.applemvp.factroy

import android.os.Bundle
import android.util.Log
import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView
import java.util.ArrayList

/**
 * applehsp
 */
class PresenterProxyFactroyImpl  constructor(override var presenterFactory: IMvpPresenterFactroy<BasePresenter<out IBaseView>>) : IPresenterProxyFactroy {
    /**
     * Presenter工厂类
     */
    var mFactory: IMvpPresenterFactroy<BasePresenter<out IBaseView>> ? = null
    var mPresenter: ArrayList<BasePresenter<out IBaseView>>? = null
    private var mBundle: Bundle? = null
    private var mIsAttchView = false

    /**
     * 绑定Presenter和view
     */
    fun onCreate(mvpView: IBaseView) {
        mPresenter = mFactory!!.createMvpPresenter()
        Log.e("perfect-mvp", "Proxy onCreate$mPresenter")
        if (mPresenter != null && !mIsAttchView) {
            for (presenter in mPresenter!!) {
                presenter.attatchView(mvpView)
            }
            mIsAttchView = true
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private fun onDetachMvpView() {
        Log.e("perfect-mvp", "Proxy onDetachMvpView = ")
        if (mPresenter != null && mIsAttchView) {
            for (presenter in mPresenter!!) {
                presenter.detachView()
            }
            mIsAttchView = false
        }
    }

    /**
     * 销毁Presenter
     */
    fun onDestroy() {
        Log.e("perfect-mvp", "Proxy onDestroy = ")
        if (mPresenter != null) {
            onDetachMvpView()
            mPresenter = null
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    fun onSaveInstanceState(): Bundle {
        Log.e("perfect-mvp", "Proxy onSaveInstanceState = ")
        val bundle = Bundle()
        // getMvpPresenter();
        if (mPresenter != null) {
            val presenterBundle = Bundle()
            //回调Presenter
            for (presenter in mPresenter!!) {
                presenter.onSaveInstanceState(presenterBundle)
            }
            bundle.putBundle(PRESENTER_KEY, presenterBundle)
        }
        return bundle
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState = ")
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState Presenter = $mPresenter")
        mBundle = savedInstanceState
    }

    companion object {
        /**
         * 获取onSaveInstanceState中bundle的key
         */
        private const val PRESENTER_KEY = "presenter_key"
    }

    init {
        mFactory = presenterFactory
    }
}