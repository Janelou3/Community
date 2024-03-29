package com.gaojianhui.controller;

import com.gaojianhui.dto.PaginationDTO;
import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import com.gaojianhui.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/8/27 0027.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"/","/index"})
    public String toIndexPage(Model model,
                              @RequestParam(name = "page",defaultValue = "1") Integer page,
                              @RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDTO paginationDTO = questionService.list(page,size);
        if (paginationDTO == null ){
            return "noQuestion";
        }
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}