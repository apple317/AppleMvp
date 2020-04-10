package com.base.applemvp.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import java.util.*

/**
 * @author applehsp
 */
@Keep
class ViewManager private constructor() {


    val allFragment: List<BaseFragment>?
        get() = if (fragmentList != null) {
            fragmentList
        } else null

    private object ViewManagerHolder {
        val sInstance = ViewManager()
    }

    fun addFragment(index: Int, fragment: BaseFragment) {
        if (fragmentList == null) {
            fragmentList = ArrayList()
        }
        fragmentList!!.add(index, fragment)
    }


    fun getFragment(index: Int): BaseFragment? {
        return if (fragmentList != null) {
            fragmentList!![index]
        } else null
    }


    /**
     * 添加指定Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }


    /**
     * 获取当前Activity
     */
    fun currentActivity(): Activity {
        return activityStack!!.lastElement()
    }


    /**
     * 结束当前Activity
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }


    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }


    /**
     * 结束指定Class的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                return
            }
        }
    }


    /**
     * 结束全部的Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }


    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        try {
            finishAllActivity()
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
            activityManager.killBackgroundProcesses(context.packageName)
            //System.exit(0);
        } catch (e: Exception) {
            Log.e("ActivityManager", "app exit" + e.message)
        }

    }

    companion object {

        private var activityStack: Stack<Activity>? = null
        private var fragmentList: MutableList<BaseFragment>? = null

        val instance: ViewManager
            get() = ViewManagerHolder.sInstance
    }
}