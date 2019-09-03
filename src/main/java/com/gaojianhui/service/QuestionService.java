package com.gaojianhui.service;

import com.gaojianhui.dto.PaginationDTO;
import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.QuestionMapper;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.Question;
import com.gaojianhui.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.PGPData;
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

    public PaginationDTO list(Integer page, Integer size){
        int totalPages;
        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0){
            totalPages = totalCount / size;
        } else {
            totalPages = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page > totalPages){
            page = totalPages;
        }
        int offset = size * (page - 1);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalPages,page,size);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Long userId,Integer page, Integer size){
        int totalPages;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount == 0){
            return null;
        }
        if (totalCount % size == 0){
            totalPages = totalCount / size;
        } else {
            totalPages = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page > totalPages){
            page = totalPages;
        }
        int offset = size * (page - 1);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalPages,page,size);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }
}

