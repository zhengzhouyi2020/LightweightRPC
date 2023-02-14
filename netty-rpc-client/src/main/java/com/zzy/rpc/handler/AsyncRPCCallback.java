package com.zzy.rpc.handler;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */
public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);
}
