package com.base.applemvp.common

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.base.applemvp.common.ViewManager.Companion.instance
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 * @author applehsp
 */
@SuppressLint("Registered")
abstract class BaseActivity : RxAppCompatActivity() {

    var currentFragment: Fragment? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        instance.addActivity(this)
        initView(savedInstanceState)
        initData()
    }

    fun switchFragment(fragment: Fragment, layoutID: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment !== currentFragment) {
            if (currentFragment == null) {
                transaction.add(layoutID, fragment).commitAllowingStateLoss()
                currentFragment = fragment
                return
            }
            if (!fragment.isAdded) {
                transaction.hide(currentFragment!!).add(layoutID, fragment).commitAllowingStateLoss()
            } else {
                transaction.hide(currentFragment!!).show(fragment).commitAllowingStateLoss()
            }
        }
        currentFragment = fragment
    }

    /**
     * 子类必须重写
     */
    abstract fun setLayoutId(): Int

    protected abstract fun initData()
    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        hintKeyBoard()
        instance.finishActivity(this)
    }

    /**
     * 弹出栈顶部的Fragment
     */
    fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    /**
     * 打开软键盘
     */
    fun openKeyBoard(editText: View?) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager?.showSoftInput(editText, 0)
    }

    fun hintKeyBoard() { //拿到InputMethodManager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //如果window上view获取焦点 && view不为空
        if (imm.isActive && currentFocus != null) { //拿到view的token 不为空
            if (currentFocus.windowToken != null) { //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
}