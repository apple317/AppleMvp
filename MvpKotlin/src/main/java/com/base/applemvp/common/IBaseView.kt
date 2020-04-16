package com.base.applemvp.common

import com.trello.rxlifecycle3.LifecycleTransformer

/**
 *
 * View接口的基类
 *
 * @author applehsp
 * @name IBaseView
 */
interface IBaseView {
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param
     * @return
     */
    fun <T> bindLifeycle(loadProgress: Boolean): LifecycleTransformer<T>

    /**
     * 显示隐藏转圈
     */
    fun showProgress(loading: Boolean)

}