package com.zzy.rpc.route.impl;

import com.zzy.rpc.protocol.RpcProtocol;
import com.zzy.rpc.route.RpcLoadBalance;

import java.util.Map;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description: LRU load balance
 */
public class RpcLoadBalanceLRU extends RpcLoadBalance {
    @Override
    public RpcProtocol route(String serviceKey, Map<RpcProtocol, RpcClientHandler> connectedServerNodes) throws Exception {
        return null;
    }
}
