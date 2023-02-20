package com.zzy.netty.test.server;


import com.zzy.netty.test.service.*;
import com.zzy.rpc.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcServerBootstrap2 {
    private static final Logger logger = LoggerFactory.getLogger(RpcServerBootstrap2.class);

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1:18877";
        String registryAddress = "10.217.59.164:2181";
        RpcServer rpcServer = new RpcServer(serverAddress, registryAddress);
        HelloService helloService1 = new HelloServiceImpl();
        rpcServer.addService(HelloService.class.getName(), "1.0", helloService1);
        HelloService helloService2 = new HelloServiceImpl2();
        rpcServer.addService(HelloService.class.getName(), "2.0", helloService2);
        PersonService personService = new PersonServiceImpl();
        rpcServer.addService(PersonService.class.getName(), "", personService);
        try {
            rpcServer.start();
        } catch (Exception ex) {
            logger.error("Exception: {}", ex.toString());
        }
    }
}
