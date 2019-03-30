package com.cyl.utils;

import java.lang.annotation.*;

/**
 * Created by cyl on 2019-03-11.
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreProperty {

    /**
     * 指定类
     * @return
     */
    Class<?> pojo();

    /**
     * 指定上面的类那些属性需要过滤的
     * @return
     */
    String[] value();
}
