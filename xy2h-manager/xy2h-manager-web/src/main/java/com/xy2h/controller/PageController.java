package com.xy2h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/19
 * @Description 页面跳转
 */

@Controller
public class PageController {
    //打开首页
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    //打开其他页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
