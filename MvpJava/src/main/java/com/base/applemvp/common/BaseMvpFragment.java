package com.base.applemvp.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.base.applemvp.factroy.IMvpPresenterFactroy;
import com.base.applemvp.factroy.IPresenterProxyFactroy;
import com.base.applemvp.factroy.MvpPresenterFactroyImpl;
import com.base.applemvp.factroy.PresenterProxyFactroyImpl;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.FragmentEvent;


/**
 * @author applehsp
 */


public abstract class BaseMvpFragment extends BaseFragment implements IBaseView, IPresenterProxyFactroy {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";


    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxyFactroyImpl mProxy=new PresenterProxyFactroyImpl<>(MvpPresenterFactroyImpl.createrFragment(this));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HU","onCreateView=BaseMvpFragment==="+mProxy);
        mProxy.onCreate(this);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
//        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
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

    @Override
    public <T> LifecycleTransformer<T> bindLifeycle(boolean loading) {
        if (loading) {
            showProgress();
        }
        return this.bindUntilEvent(FragmentEvent.DESTROY);
    }

}
