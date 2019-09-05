package com.gaojianhui.controller;

import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.QuestionMapper;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.Question;
import com.gaojianhui.model.User;
import com.gaojianhui.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/28 0028.
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String toPublishPage(Model model){
        model.addAttribute("section","程序猿社区_发布");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "id",required = false) Long id,
                            @RequestParam(name = "title",required = false) String title,
                            @RequestParam(name = "description",required = false) String description,
                            @RequestParam(name = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("section","程序猿社区_发布");
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (description == null || description.equals("")){
            model.addAttribute("error","内容不能为空！");
            return "publish";
        }
        if (tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        //question.setGmtCreate(System.currentTimeMillis());
        //question.setGmtModified(question.getGmtCreate());
        //questionMapper.insertQuestion(question);
        questionService.createOrUpdateQuestion(question);
        return "redirect:/index";
    }

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable(name = "id") Long id,
                               Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("section","程序猿社区_修改");
        model.addAttribute("id",id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        return "publish";
    }
}
