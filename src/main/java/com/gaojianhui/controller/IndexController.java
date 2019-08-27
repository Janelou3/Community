package com.gaojianhui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2019/8/27 0027.
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/","/index"})
    public String toIndexPage(){
        return "index";
    }
}
