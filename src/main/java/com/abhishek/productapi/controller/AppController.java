package com.abhishek.productapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author siabhis
 **/
@RequestMapping("/api")
public class AppController {

    @GetMapping("/health")
    public String health(){
        return "Application is up and running";
    }
    @GetMapping("/info")
    public Map<String,String> info(){
        Map<String,String> map = new HashMap<>();
        map.put("appName","Product API");
        map.put("appVersion","1.0.0");
        map.put("developer","Abhishek Singh");
        return map;

    }
}
