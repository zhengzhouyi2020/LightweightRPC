package com.zzy.rpc.core;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */
public abstract class Server {

    /**
     * start server
     *
     */
    public abstract void start() throws Exception;

    /**
     * stop server
     *
     */
    public abstract void stop() throws Exception;
}
