package com.zzy.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description: RPC annotation for RPC service
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ZzyRpcService {
    Class<?> value();

    String version() default "";
}
