package com.base.applemvp.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 * @author applehsp
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends RxAppCompatActivity {

    Fragment currentFragment;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ViewManager.getInstance().addActivity(this);
        initView(savedInstanceState);
        initData();
    }



    public void switchFragment(Fragment fragment, int layoutID) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != currentFragment) {
            if (currentFragment == null) {
                transaction.add(layoutID, fragment).commitAllowingStateLoss();
                currentFragment = fragment;
                return;
            }
            if (!fragment.isAdded()) {
                transaction.hide(currentFragment).add(layoutID, fragment).commitAllowingStateLoss();
            } else {
                transaction.hide(currentFragment).show(fragment).commitAllowingStateLoss();
            }
        }
        currentFragment = fragment;
    }

    /**
     * 子类必须重写
     */
    public abstract int setLayoutId();

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hintKeyBoard();
        ViewManager.getInstance().finishActivity(this);
    }




    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    /**
     * 打开软键盘
     */
    public void openKeyBoard(final View editText) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.showSoftInput(editText,0);
        }
    }


    public void hintKeyBoard() {
        //拿到InputMethodManager
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果window上view获取焦点 && view不为空
        if (imm.isActive() && getCurrentFocus() != null) {
            //拿到view的token 不为空
            if (getCurrentFocus().getWindowToken() != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
