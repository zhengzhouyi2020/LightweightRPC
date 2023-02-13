package com.zzy.rpc.config;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description: ZooKeeper constant
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;
    int ZK_CONNECTION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";

    String ZK_NAMESPACE = "zzy-rpc";

}
