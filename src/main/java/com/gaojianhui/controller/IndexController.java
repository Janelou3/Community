package com.gaojianhui.controller;

import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/27 0027.
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @GetMapping(value = {"/","/index"})
    public String toIndexPage(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
