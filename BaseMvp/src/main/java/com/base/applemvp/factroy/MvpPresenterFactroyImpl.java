package com.base.applemvp.factroy;


import android.content.Context;
import android.util.Log;

import com.base.applemvp.annotations.CreatePresenterAnnotation;
import com.base.applemvp.common.BasePresenter;
import com.base.applemvp.common.IBaseView;
import com.trello.rxlifecycle3.components.RxFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * applehsp
 * <p>
 * 创建presenter
 * 动态 工厂的实现类
 */

public class MvpPresenterFactroyImpl<V extends IBaseView, P extends BasePresenter<V>> implements IMvpPresenterFactroy<P> {


    private final List<P> mPresenterClass;

    private MvpPresenterFactroyImpl(List<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }


    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param <V>    当前View实现的接口类型
     * @param <P>    当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView, P extends BasePresenter<V>> MvpPresenterFactroyImpl createrFragment(RxFragment fragment) {
        //拿到创建presenter的注解
        List<BasePresenter> currentPresenter = new ArrayList<>();
        try{
            Field[] fields = fragment.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CreatePresenterAnnotation.class)) {
                    Log.e("HU","MvpPresenterFactroyImpl=11111==="+fields);
                    CreatePresenterAnnotation address = field.getAnnotation(CreatePresenterAnnotation.class);
                    Class<? extends BasePresenter> presenter = address.value();
                    try {
                        field.setAccessible(true); //设置属性值的访问权限
                        BasePresenter presenter1 = presenter.newInstance();
                        Log.e("HU","MvpPresenterFactroyImpl=3333=presenter1=="+presenter1);
                        field.set(fragment, presenter1); //将查找到的view指定给目标对象object
                        Log.e("HU","MvpPresenterFactroyImpl=444=presenter1=="+presenter1);
                        currentPresenter.add(presenter1);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            Log.e("HU","MvpPresenterFactroyImpl=2222==="+e.toString());
            e.printStackTrace();
        }
        return new MvpPresenterFactroyImpl(currentPresenter);
    }

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param <V>    当前View实现的接口类型
     * @param <P>    当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView, P extends BasePresenter<V>> MvpPresenterFactroyImpl creater(Context context) {
        //拿到创建presenter的注解
        List<BasePresenter> currentPresenter = new ArrayList<>();
        Log.e("HU","MvpPresenterFactroyImpl=creater==="+context);
        try{
            Field[] fields = context.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CreatePresenterAnnotation.class)) {
                    Log.e("HU","MvpPresenterFactroyImpl=11111==="+fields);
                    CreatePresenterAnnotation address = field.getAnnotation(CreatePresenterAnnotation.class);
                    Class<? extends BasePresenter> presenter = address.value();
                    try {
                        field.setAccessible(true); //设置属性值的访问权限
                        BasePresenter presenter1 = presenter.newInstance();
                        field.set(context, presenter1); //将查找到的view指定给目标对象object
                        currentPresenter.add(presenter1);
                    } catch (IllegalAccessException e) {
                        Log.e("HU","MvpPresenterFactroyImpl=IllegalAccessException==="+context);
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        Log.e("HU","MvpPresenterFactroyImpl=InstantiationException==="+context);

                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            Log.e("HU","MvpPresenterFactroyImpl=2222==="+context);
            e.printStackTrace();
        }
        return new MvpPresenterFactroyImpl(currentPresenter);
    }


    @Override
    public List<P> createMvpPresenter() {
        try {
            return mPresenterClass;
        } catch (Exception e) {
            return null;
//            throw new RuntimeException("注解为空,请检查类上是否声明了@CreatePresenterAnnotation(xxx,class)注解");
        }
    }
}
