package com.app.applemvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.applemvp.common.BaseMvpFragment


class UserFragment : BaseMvpFragment() {

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main,null);
    }


    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun showProgress(loading: Boolean) {
    }


}