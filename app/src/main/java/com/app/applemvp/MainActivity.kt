package com.app.applemvp

import android.os.Bundle
import com.app.applemvp.mvp.MainPresenter
import com.app.applemvp.mvp.MainView
import com.app.applemvp.mvp.SplashPresenter
import com.app.applemvp.mvp.SplashView
import com.base.applemvp.annotations.CreatePresenterAnnotation
import com.base.applemvp.common.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity(), SplashView, MainView {
    @CreatePresenterAnnotation(SplashPresenter::class)
    var splashPresenter: SplashPresenter? = null
    @CreatePresenterAnnotation(MainPresenter::class)
    var mainPresenter: MainPresenter? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        btnAdvert.setOnClickListener { splashPresenter?.advertList("kotlin 广告") }
        btnSync.setOnClickListener { mainPresenter?.appSync("kotlin 同步") }
    }

    override fun initView(savedInstanceState: Bundle) {}
    override fun appSync(msg: String) {
        btnSync.text = msg
    }

    override fun advertList(advert: String) {
        btnAdvert.text = advert
    }
}