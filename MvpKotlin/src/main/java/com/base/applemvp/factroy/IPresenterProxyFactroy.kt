package com.base.applemvp.factroy

import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView

/**
 * applehsp
 *
 *
 * 提供get,set工厂方法
 *
 *
 * 目的为了扩展，可以自定义工厂，创建不同的presenter
 */
interface IPresenterProxyFactroy {

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    var presenterFactory: IMvpPresenterFactroy<BasePresenter<out IBaseView>>
    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
}