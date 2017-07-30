package com.czm.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mac on 17/7/29.
 */
@RestController
@RequestMapping("/api/demo")
public class DemoApi {
    @GetMapping(value = "test")
    public String test(){
        return "hello world";
    }
}
