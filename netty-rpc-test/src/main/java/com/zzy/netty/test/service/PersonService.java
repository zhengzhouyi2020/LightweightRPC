package com.zzy.netty.test.service;

import java.util.List;

/**
 * 
 */
public interface PersonService {
    List<Person> callPerson(String name, Integer num);
}
