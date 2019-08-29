package com.gaojianhui.controller;

import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import com.gaojianhui.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/8/27 0027.
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping(value = {"/","/index"})
    public String toIndexPage(HttpServletRequest request,
                              Model model){
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
        List<QuestionDTO> questionDTOS = questionService.list();
        model.addAttribute("questions",questionDTOS);
        return "index";
    }
}
