package com.base.applemvp.common

import android.os.Bundle
import android.util.Log
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 *
 * Presenter的基类
 *
 * @author applehsp
 * * 使用动态代理模式设计basePresenter的初忠：
 * 因为在使用mvp模式开发中，会遇到一些问题：
 * 比如：activity持有presenter的引用，presenter持有view的引用 ，这就导致几个问题，
 * 1.当activity销毁后，由于p持有activity的引用 导致activity无法释放，最终会引用内存泄漏
 *
 *
 * 2.p层处理完逻辑，调用v层来处理UI，怎么拿到V层的引用
 *
 *
 * 3.请求网络时，网络不太好，在这个时候用户没等到请求完成退出该页面，等获取数据成功之后再拿V的引用 ，这个时候view 有可能被销毁，v层的引用为空
 */
abstract class BasePresenter<V : IBaseView> {
    /**
     * 解决第一个问题
     *
     *
     * 弱引用, 防止内存泄漏
     */
    var weak =Weak<V>()
    /**
     * 解决第二个问题
     */
    var view: V? = null

    /**
     * 关联V层和P层
     *
     */
    @Suppress("UNCHECKED_CAST")
    fun attatchView(v: IBaseView) {
        weak.weakReference=WeakReference(v as V)
        val viewHandler = weak.weakReference.get()?.let { MvpViewHandler(it) }
        view = Proxy.newProxyInstance(this::class.java.classLoader, v.javaClass.interfaces,viewHandler) as V?
    }

    /**
     * @return P层和V层是否关联.
     */
    val isViewAttached: Boolean
        get() = weak.weakReference.get() != null

    /**
     * 断开V层和P层
     * 在Acitivity的onDestory()中调用
     */
    fun detachView() {
        if (isViewAttached) {
            weak.weakReference.clear()
        }
    }

    /**
     * Presenter被销毁时调用
     */
    fun onDestroyPersenter() {
        Log.e("perfect-mvp", "P onDestroy = ")
    }

    var statueBundle:Bundle ?=null

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     */
    fun onSaveInstanceState(presenterBundle: Bundle) {
        statueBundle=presenterBundle
    }

    /**
     * 动态代理类
     */
    private inner class MvpViewHandler internal constructor(private val mvpView: IBaseView) : InvocationHandler {
        @Throws(Throwable::class)
        override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? { //解决第三个问题，如果V层没被销毁, 执行V层的方法.
            try {
                if (isViewAttached) {
                    return method.invoke(mvpView, *args)
                }
            } catch (e: InvocationTargetException) {
                throw e.cause!!
            }
            //P层不需要关注V层的返回值
            return null
        }

    }
}