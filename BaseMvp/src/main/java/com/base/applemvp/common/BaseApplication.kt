package com.base.applemvp.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * @author applehsp
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mApplication = this
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
}