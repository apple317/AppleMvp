package com.app.applemvp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.applemvp.databinding.ActivityMainBinding
import com.app.applemvp.mvp.MainPresenter
import com.app.applemvp.mvp.MainView
import com.base.applemvp.annotations.CreatePresenterAnnotation
import com.base.applemvp.common.BaseMvpActivity


class MainActivity : BaseMvpActivity(), MainView {

    lateinit var binding: ActivityMainBinding
    lateinit var entity:MainEntity
    @CreatePresenterAnnotation(MainPresenter::class)
    var mainPresenter: MainPresenter? = null


    override fun initData() {
        Log.e("HU", "initData======" + mainPresenter);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        entity= MainEntity(application)
        binding.userInfo = entity
        binding.mainAct=this
        mainPresenter?.appSync("xxxxxxx")
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun appSync(msg: String) {
        Log.e("HU", "appSync===xx===" + msg);
        entity.name.postValue(msg)
    }


    fun  onClick(view:View){
        Log.e("HU", "onClick")
        entity.name.postValue("mmmmm"+view.id.toString())
    }


}