package com.base.applemvp.factroy;

import android.os.Bundle;
import android.util.Log;

import com.base.applemvp.common.BasePresenter;
import com.base.applemvp.common.IBaseView;

import java.util.List;


/**
 * applehsp
 */

public class PresenterProxyFactroyImpl<P extends BasePresenter> implements IPresenterProxyFactroy {
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";
    /**
     * Presenter工厂类
     */
    private IMvpPresenterFactroy<P> mFactory;
    public List<P> mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public PresenterProxyFactroyImpl(IMvpPresenterFactroy presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }


    @Override
    public void setPresenterFactory(IMvpPresenterFactroy presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    @Override
    public IMvpPresenterFactroy getPresenterFactory() {
        return mFactory;
    }


    /**
     * 绑定Presenter和view
     */
    public void onCreate(IBaseView mvpView) {
        mPresenter = mFactory.createMvpPresenter();
        Log.e("perfect-mvp", "Proxy onCreate" + mPresenter);
        if (mPresenter != null && !mIsAttchView) {
            for (P presenter : mPresenter) {
                presenter.attatchView(mvpView);
            }
            mIsAttchView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        Log.e("perfect-mvp", "Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttchView) {
            for (P presenter : mPresenter) {
                presenter.detachView();
                presenter = null;
            }
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        Log.e("perfect-mvp", "Proxy onDestroy = ");
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Log.e("perfect-mvp", "Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        // getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            for (P presenter : mPresenter) {
                presenter.onSaveInstanceState(presenterBundle);
            }
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }


    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState = ");
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }


}
