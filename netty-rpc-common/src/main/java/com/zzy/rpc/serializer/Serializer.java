package com.zzy.rpc.serializer;

/**
 * @Author:zzy
 * @Date:2023/2/13
 * @Description:
 */

public abstract class Serializer {
    public abstract <T> byte[] serialize(T obj);

    public abstract <T> Object deserialize(byte[] bytes, Class<T> clazz);
}


