package com.base.applemvp.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle3.components.support.RxFragment
/**
 * @author applehsp
 */
abstract class BaseFragmentX : RxFragment() {
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


}