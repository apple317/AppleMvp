package com.base.applemvp.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.base.applemvp.annotations.CreatePresenterAnnotation
import com.trello.rxlifecycle3.LifecycleTransformer
import java.util.ArrayList
import kotlin.reflect.KClass

/**
 * @author applehsp
 */
abstract class BaseMvpActivity : BaseActivity(), IBaseView {
    /**
     * 绑定生命周期
     * @return
     */
    override fun <T> bindLifeycle(loading: Boolean): LifecycleTransformer<T> {
        if (loading) {
            showProgress()
        }
        return bindToLifecycle()
    }

    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        initMvp(this)
        super.onCreate(savedInstanceState)
    }

    val currentPresenter: MutableList<BasePresenter<out IBaseView>> = ArrayList<BasePresenter<out IBaseView>>()
    fun initMvp(context: Context) {
        try {
            val fields = context.javaClass.declaredFields
            for (field in fields) {
                if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                    Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                    val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                    val presenter: KClass<out BasePresenter<out IBaseView>> = address.value
                    try {
                        field.isAccessible = true //设置属性值的访问权限
                        val presenter1: BasePresenter<*> = presenter.java.newInstance()
                        field[context] = presenter1 //将查找到的view指定给目标对象object
                        currentPresenter.add(presenter1)
                    } catch (e: IllegalAccessException) {
                        Log.e("HU", "MvpPresenterFactroyImpl=IllegalAccessException===$context")
                        throw RuntimeException(e)
                    } catch (e: InstantiationException) {
                        Log.e("HU", "MvpPresenterFactroyImpl=InstantiationException===$context")
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("HU", "MvpPresenterFactroyImpl=2222===$context")
            e.printStackTrace()
        }
        for (presenter in currentPresenter!!) {
            presenter.attatchView(this)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("perfect-mvp", "V onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        /**
         * 销毁Presenter
         */
        if (currentPresenter != null) {
            for (presenter in currentPresenter!!) {
                presenter.attatchView(this)
            }
        }
        Log.e("perfect-mvp", "V onDestroy = ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("perfect-mvp", "V onSaveInstanceState")
    }


    override fun showProgress() {}
    override fun hideProgress() {}


}