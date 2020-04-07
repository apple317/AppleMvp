package com.base.applemvp.annotations;



import com.base.applemvp.common.BasePresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author applehsp
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface CreatePresenterAnnotation {
    Class<? extends BasePresenter> value();
}
