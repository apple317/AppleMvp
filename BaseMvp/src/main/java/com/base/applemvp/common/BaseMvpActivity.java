package com.base.applemvp.common;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.base.applemvp.factroy.IMvpPresenterFactroy;
import com.base.applemvp.factroy.IPresenterProxyFactroy;
import com.base.applemvp.factroy.MvpPresenterFactroyImpl;
import com.base.applemvp.factroy.PresenterProxyFactroyImpl;
import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author applehsp
 */

public abstract class BaseMvpActivity extends BaseActivity implements IBaseView, IPresenterProxyFactroy {


    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";


    /**
     * 绑定生命周期
     * @return
     */
    @Override
    public <T> LifecycleTransformer<T> bindLifeycle(boolean loading) {
        if (loading) {
            showProgress();
        }
        return this.bindToLifecycle();
    }


    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxyFactroyImpl mProxy = new PresenterProxyFactroyImpl<>(MvpPresenterFactroyImpl.creater(this));


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mProxy.onCreate(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
    }




    @Override
    public void setPresenterFactory(IMvpPresenterFactroy presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IMvpPresenterFactroy getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


}
