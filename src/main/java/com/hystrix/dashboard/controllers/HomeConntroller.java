package com.hystrix.dashboard.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeConntroller {


    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "fallBackHello",groupKey = "hello"
    ,commandKey = "hello",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            })
    public String home(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Failed");
        }
        return "hello hystrix testing ";
    }
    public String fallBackHello(){
        return "Failed ! fallBack ";
    }
}
