package com.yichen.common.injection.scope

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Chen on 2019/2/13
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope