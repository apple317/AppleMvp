package com.base.applemvp.factroy

import android.content.Context
import android.util.Log
import com.base.applemvp.annotations.CreatePresenterAnnotation
import com.base.applemvp.common.BaseFragment
import com.base.applemvp.common.BaseFragmentX
import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView
import kotlin.collections.ArrayList
import kotlin.reflect.KClass

/**
 * applehsp
 *
 *
 * 创建presenter
 * 动态 工厂的实现类
 */
class MvpPresenterFactroyImpl   constructor(presenterClass: ArrayList<BasePresenter<out IBaseView>>) : IMvpPresenterFactroy<BasePresenter<out IBaseView>> {


    companion object {
        /**
         * 根据注解创建Presenter的工厂实现类
         *
         * @param <V>    当前View实现的接口类型
         * @param <P>    当前要创建的Presenter类型
         * @return 工厂类
        </P></V> */
        fun  mvpCreate(fragment: BaseFragment): MvpPresenterFactroyImpl { //拿到创建presenter的注解
            val currentPresenter: ArrayList<BasePresenter<out IBaseView>> = ArrayList<BasePresenter<out IBaseView>>()
            try {
                val fields = fragment.javaClass.declaredFields
                for (field in fields) {
                    if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                        Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                        val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                        val presenter: KClass<out BasePresenter<out IBaseView>> = address.value
                        try {
                            field.isAccessible = true //设置属性值的访问权限
                            val presenter1: BasePresenter<out IBaseView> = presenter.java.newInstance()!!
                            Log.e("HU", "MvpPresenterFactroyImpl=3333=presenter1==$presenter1")
                            field[fragment] = presenter1 //将查找到的view指定给目标对象object
                            Log.e("HU", "MvpPresenterFactroyImpl=444=presenter1==$presenter1")
                            currentPresenter.add(presenter1)
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: InstantiationException) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("HU", "MvpPresenterFactroyImpl=2222===$e")
                e.printStackTrace()
            }
            return MvpPresenterFactroyImpl(currentPresenter)
        }

        fun  mvpCreate(fragment: BaseFragmentX): MvpPresenterFactroyImpl { //拿到创建presenter的注解
            val currentPresenter: ArrayList<BasePresenter<out IBaseView>> = ArrayList<BasePresenter<out IBaseView>>()
            try {
                val fields = fragment.javaClass.declaredFields
                for (field in fields) {
                    if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                        Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                        val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                        val presenter: KClass<out BasePresenter<out IBaseView>> = address.value
                        try {
                            field.isAccessible = true //设置属性值的访问权限
                            val presenter1: BasePresenter<out IBaseView> = presenter.java.newInstance()!!
                            Log.e("HU", "MvpPresenterFactroyImpl=3333=presenter1==$presenter1")
                            field[fragment] = presenter1 //将查找到的view指定给目标对象object
                            Log.e("HU", "MvpPresenterFactroyImpl=444=presenter1==$presenter1")
                            currentPresenter.add(presenter1)
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: InstantiationException) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("HU", "MvpPresenterFactroyImpl=2222===$e")
                e.printStackTrace()
            }
            return MvpPresenterFactroyImpl(currentPresenter)
        }
        /**
         * 根据注解创建Presenter的工厂实现类
         *
         * @param <V>    当前View实现的接口类型
         * @param <P>    当前要创建的Presenter类型
         * @return 工厂类
        </P></V> */
        fun  mvpCreate(context: Context): MvpPresenterFactroyImpl{ //拿到创建presenter的注解
            val currentPresenter: ArrayList<BasePresenter<out IBaseView>> = ArrayList<BasePresenter<out IBaseView>>()
            Log.e("HU", "MvpPresenterFactroyImpl=creater===$context")
            try {
                val fields = context.javaClass.declaredFields
                for (field in fields) {
                    if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                        Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                        val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                        val presenter: KClass<out BasePresenter<out IBaseView>> = address.value
                        try {
                            field.isAccessible = true //设置属性值的访问权限
                            val presenter1: BasePresenter<out IBaseView> = presenter.java.newInstance()!!
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
            return MvpPresenterFactroyImpl(currentPresenter)
        }
    }

    private var mvpPresenterFactroyImpl: ArrayList<BasePresenter<out IBaseView>>? = null


    init {
        mvpPresenterFactroyImpl = presenterClass
    }


    override fun createMvpPresenter(): ArrayList<BasePresenter<out IBaseView>> {
        return try {
            mvpPresenterFactroyImpl
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } as ArrayList<BasePresenter<out IBaseView>>
    }

}
