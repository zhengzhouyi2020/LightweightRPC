package com.zzy.netty.test.service;


import com.zzy.rpc.annotation.ZzyRpcService;

@ZzyRpcService(value = HelloService.class, version = "2.0")
public class HelloServiceImpl2 implements HelloService {

    public HelloServiceImpl2() {

    }

    @Override
    public String hello(String name) {
        return "Hi " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hi " + person.getFirstName() + " " + person.getLastName();
    }

    @Override
    public String hello(String name, Integer age) {
        return name + " is " + age + " years old";
    }
}
