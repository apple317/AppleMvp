package com.base.applemvp.factroy

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.base.applemvp.annotations.CreatePresenterAnnotation
import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView
import java.util.*

/**
 * applehsp
 *
 *
 * 创建presenter
 * 动态 工厂的实现类
 */
class MvpPresenterFactroyImpl<V : IBaseView, P : BasePresenter<V>> private constructor(presenterClass: List<P>) : IMvpPresenterFactroy<P> {
    private val mPresenterClass: List<P>
    override fun createMvpPresenter(): List<P> {
        return try {
            mPresenterClass
        } catch (e: Exception) {
            emptyList()
        }
    }

    companion object {
        /**
         * 根据注解创建Presenter的工厂实现类
         *
         * @param <V>    当前View实现的接口类型
         * @param <P>    当前要创建的Presenter类型
         * @return 工厂类
        </P></V> */
        fun <V : IBaseView, P : BasePresenter<V>> createrFragment(fragment: Fragment): MvpPresenterFactroyImpl<*, *> { //拿到创建presenter的注解
            val currentPresenter: MutableList<BasePresenter> = ArrayList<BasePresenter>()
            try {
                val fields = fragment.javaClass.declaredFields
                for (field in fields) {
                    if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                        Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                        val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                        val presenter: Class<BasePresenter> = address.value()
                        try {
                            field.isAccessible = true //设置属性值的访问权限
                            val presenter1: BasePresenter? = presenter.newInstance()
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
            return MvpPresenterFactroyImpl<Any?, Any?>(currentPresenter)
        }

        /**
         * 根据注解创建Presenter的工厂实现类
         *
         * @param <V>    当前View实现的接口类型
         * @param <P>    当前要创建的Presenter类型
         * @return 工厂类
        </P></V> */
        fun <V : IBaseView?, P : BasePresenter<V>> creater(context: Context): MvpPresenterFactroyImpl<*, *> { //拿到创建presenter的注解
            val currentPresenter: MutableList<BasePresenter> = ArrayList<BasePresenter>()
            Log.e("HU", "MvpPresenterFactroyImpl=creater===$context")
            try {
                val fields = context.javaClass.declaredFields
                for (field in fields) {
                    if (field.isAnnotationPresent(CreatePresenterAnnotation::class.java)) {
                        Log.e("HU", "MvpPresenterFactroyImpl=11111===$fields")
                        val address = field.getAnnotation(CreatePresenterAnnotation::class.java)
                        val presenter: Class<out BasePresenter> = address.value()
                        try {
                            field.isAccessible = true //设置属性值的访问权限
                            val presenter1: BasePresenter = presenter.newInstance()
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
            return MvpPresenterFactroyImpl<Any, Any>(currentPresenter)
        }
    }

    init {
        mPresenterClass = presenterClass
    }
}
