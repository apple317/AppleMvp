package com.base.applemvp.common

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.multidex.MultiDex

/**
 * @author applehsp
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mApplication = this
        getApplicationLifecycles()
        for (lifecycle in mAppLifecycles) {
            lifecycle.onCreate(this)
        }
    }
    override fun onTerminate() {
        super.onTerminate()
        // you must install multiDex whatever tinker is installed!
        for (lifecycle in mAppLifecycles) {
            lifecycle.onTerminate(this)
        }
    }
    /**
     * bugly热修复
     *
     * @param base
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
        for (lifecycle in mAppLifecycles) {
            lifecycle.attachBaseContext(this)
        }
    }

    companion object {
        private var mApplication: BaseApplication? = null
        val appContext: Context?
            get() = mApplication

        /**
         * 利用单例模式获取HuoQApplication实例
         *
         * @return
         */
        val instance: BaseApplication?
            get() {
                if (null == mApplication) {
                    mApplication = BaseApplication()
                }
                return mApplication
            }
    }

    val mAppLifecycles= ArrayList<ApplicationLifecycles>()

    fun getApplicationLifecycles(){
        try {
            val appInfo: ApplicationInfo = getPackageManager().getApplicationInfo(
                    getPackageName(), PackageManager.GET_META_DATA
            )
            if (appInfo.metaData != null) {
                for (key in appInfo.metaData.keySet()) {
                    if ("AppConfig".equals(appInfo.metaData.get(key))) {
                        mAppLifecycles.add(parseModule(key))
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException(
                    "Unable to find metadata to parse ModularAppConfiger",
                    e
            )
        }
    }

    open fun parseModule(className: String): ApplicationLifecycles {
        val clazz: Class<*>
        clazz = try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException(
                    "Unable to find ModularAppConfiger implementation",
                    e
            )
        }
        val module: Any
        module = try {
            clazz.newInstance()
        } catch (e: InstantiationException) {
            throw java.lang.RuntimeException(
                    "Unable to instantiate ModularAppConfiger implementation for $clazz",
                    e
            )
        } catch (e: IllegalAccessException) {
            throw java.lang.RuntimeException(
                    "Unable to instantiate ModularAppConfiger implementation for $clazz",
                    e
            )
        }
        if (module !is ApplicationLifecycles) {
            throw java.lang.RuntimeException("Expected instanceof ModularAppConfiger, but found: $module")
        }
        return module
    }
}