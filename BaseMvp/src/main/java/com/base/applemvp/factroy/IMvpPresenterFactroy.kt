package com.base.applemvp.factroy

import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView
import java.util.ArrayList

/**
 * applehsp
 *
 *
 * 创建Presenter工厂接口
 */
interface IMvpPresenterFactroy<P> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    fun createMvpPresenter(): ArrayList<P>
}