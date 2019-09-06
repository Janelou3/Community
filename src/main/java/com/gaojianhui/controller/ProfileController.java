package com.gaojianhui.controller;

import com.gaojianhui.dto.PaginationDTO;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import com.gaojianhui.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/9/3 0003.
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String toProfilePage(HttpServletRequest request,
                                Model model,
                                @PathVariable(name = "action") String action,
                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/index";
        }
        if (action.equals("questions")){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.listByUserId(user.getId(),page,size);
            model.addAttribute("paginationDTO",paginationDTO);
            if (paginationDTO == null){
                return "noQuestion";
            }
        }

        return "profile";
    }
}
