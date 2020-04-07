package com.base.applemvp.factroy;


import java.util.List;

/**
 * applehsp
 * <p>
 * 创建Presenter工厂接口
 */

public interface IMvpPresenterFactroy<P> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    List<P> createMvpPresenter();
}
