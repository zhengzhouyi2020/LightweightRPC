package com.zzy.netty.test.util;

import com.zzy.netty.test.service.HelloServiceImpl;
import com.zzy.netty.test.service.Person;
import com.zzy.rpc.codec.RpcRequest;
import com.zzy.rpc.codec.RpcResponse;
import com.zzy.rpc.util.JsonUtil;
import com.zzy.rpc.util.SerializationUtil;

import java.util.UUID;

/**
 * @Author:zzy
 * @Date:2023/2/14
 * @Description:
 */
public class JsonTest {
    public static void main(String[] args) {
        RpcResponse response = new RpcResponse();
        response.setRequestId(UUID.randomUUID().toString());
        response.setError("Error msg");
        System.out.println(response.getRequestId());

        byte[] datas = JsonUtil.serialize(response);
        System.out.println("Json byte length: " + datas.length);

        byte[] datas2 = SerializationUtil.serialize(response);
        System.out.println("Protobuf byte length: " + datas2.length);

        RpcResponse resp = (RpcResponse) JsonUtil.deserialize(datas, RpcResponse.class);
        System.out.println(resp.getRequestId());
    }


    private static void TestJsonSerialize() {
        RpcRequest request = new RpcRequest();
        request.setClassName(HelloServiceImpl.class.getName());
        request.setMethodName(HelloServiceImpl.class.getDeclaredMethods()[0].getName());
        Person person = new Person("lu", "xiaoxun");
        request.setParameters(new Object[]{person});
        request.setRequestId(UUID.randomUUID().toString());
        System.out.println(request.getRequestId());

        byte[] datas = JsonUtil.serialize(request);
        System.out.println("Json byte length: " + datas.length);

        byte[] datas2 = SerializationUtil.serialize(request);
        System.out.println("Protobuf byte length: " + datas2.length);

        RpcRequest req = (RpcRequest) JsonUtil.deserialize(datas, RpcRequest.class);
        System.out.println(req.getRequestId());
    }
}
