package com.app.gland.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用接口层
 *
 * @author: ztan
 * @create: 2021/3/25
 **/
@Controller
@Slf4j
public class CommonRestStatic {

 
    @GetMapping("/index/page")
    public String index() {
        return "index.html";
    }

}
