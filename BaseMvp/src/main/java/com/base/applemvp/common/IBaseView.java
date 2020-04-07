package com.base.applemvp.common;


import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * <p>View接口的基类</p>
 *
 * @author applehsp
 * @name IBaseView
 */

public interface IBaseView{
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param
     * @return
     */
    <T> LifecycleTransformer<T> bindLifeycle(boolean loadProgress);

    /**
     * 显示正在加载
     */
    void showProgress();

    /**
     * 隐藏正在加载
     */
    void hideProgress();

}
