package com.zzy.rpc.proxy;

import com.zzy.rpc.handler.RpcFuture;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */
public interface RpcService<T, P, FN extends SerializableFunction<T>> {
    RpcFuture call(String funcName, Object... args) throws Exception;

    /**
     * lambda method reference
     */
    RpcFuture call(FN fn, Object... args) throws Exception;
}
