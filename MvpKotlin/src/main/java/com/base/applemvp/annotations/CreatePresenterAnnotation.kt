package com.base.applemvp.annotations

import com.base.applemvp.common.BasePresenter
import com.base.applemvp.common.IBaseView
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * @author applehsp
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class CreatePresenterAnnotation(val value: KClass<out BasePresenter<out IBaseView>>)