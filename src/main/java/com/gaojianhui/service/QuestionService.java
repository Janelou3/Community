package com.gaojianhui.service;

import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.QuestionMapper;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.Question;
import com.gaojianhui.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/29 0029.
 */
@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}

