package com.zzy.rpc.proxy;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */
@FunctionalInterface
public interface RpcFunction2<T, P1, P2> extends SerializableFunction<T> {
    Object apply(T t, P1 p1, P2 p2);
}
