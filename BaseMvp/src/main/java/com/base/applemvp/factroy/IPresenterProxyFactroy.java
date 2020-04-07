package com.base.applemvp.factroy;


/**
 * applehsp
 * <p>
 * 提供get,set工厂方法
 * <p>
 * 目的为了扩展，可以自定义工厂，创建不同的presenter
 */

public interface IPresenterProxyFactroy{

    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     *
     */
    void setPresenterFactory(IMvpPresenterFactroy presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    IMvpPresenterFactroy getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
}
