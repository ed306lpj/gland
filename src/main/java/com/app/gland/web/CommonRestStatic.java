package com.app.gland.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CommonRestStatic {

 
    @GetMapping("index/page")
    public String index() {
        return "/index";
    }
    @GetMapping("")
    public String index4() {
        return "/index4";
    }
}
