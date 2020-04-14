package com.base.applemvp.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.trello.rxlifecycle3.components.RxFragment

/**
 * @author applehsp
 */
abstract class BaseFragment : RxFragment() {
    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    var holdingActivity: BaseActivity? = null

    var rootView: View? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        holdingActivity = context as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setLayoutId(), container, false)
        initView()
        initData()
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }



     abstract fun setLayoutId(): Int
     abstract fun initView()
     abstract fun initData()

    /**
     * 弹出栈顶部的Fragment
     */
    protected fun popFragment() {
        holdingActivity!!.popFragment()
    }

    fun hintKeyBoard() { //拿到InputMethodManager
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //如果window上view获取焦点 && view不为空
        if (imm.isActive && activity.currentFocus != null) { //拿到view的token 不为空
            if (activity.currentFocus.windowToken != null) { //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
}