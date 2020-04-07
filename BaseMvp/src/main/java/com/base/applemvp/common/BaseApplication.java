package com.base.applemvp.common;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


/**
 * @author applehsp
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;



    public static Context getAppContext() {
        return mApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }


    /**
     * bugly热修复
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
    }




    /**
     * 利用单例模式获取HuoQApplication实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (null == mApplication) {
            mApplication = new BaseApplication();
        }
        return mApplication;
    }

}
