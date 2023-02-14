package com.zzy.rpc.proxy;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */
@FunctionalInterface
public interface RpcFunction<T, P> extends SerializableFunction<T>{
    Object apply(T t, P p);
}
