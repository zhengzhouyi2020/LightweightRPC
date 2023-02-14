package com.zzy.rpc.route.impl;

import com.google.common.hash.Hashing;
import com.zzy.rpc.handler.RpcClientHandler;
import com.zzy.rpc.protocol.RpcProtocol;
import com.zzy.rpc.route.RpcLoadBalance;

import java.util.List;
import java.util.Map;

import static com.dyuproject.protostuff.CollectionSchema.MessageFactories.List;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description: consistent hash balance
 */
public class RpcLoadBalanceConsistentHash extends RpcLoadBalance {
    @Override
    public RpcProtocol route(String serviceKey, Map<RpcProtocol, RpcClientHandler> connectedServerNodes) throws Exception {
        Map<String, List<RpcProtocol>> serviceMap = getServiceMap(connectedServerNodes);
        List<RpcProtocol> addressList = serviceMap.get(serviceKey);
        if(addressList != null && addressList.size() > 0) {
            return doRoute(serviceKey, addressList);
        } else {
            throw new Exception("Can not find connection for service: " + serviceKey);
        }
    }

    private RpcProtocol doRoute(String serviceKey, List<RpcProtocol> addressList) {
        int index = Hashing.consistentHash(serviceKey.hashCode(), addressList.size());
        return addressList.get(index);
    }
}
