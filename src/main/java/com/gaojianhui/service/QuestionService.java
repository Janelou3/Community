package com.gaojianhui.service;

import com.gaojianhui.dto.PaginationDTO;
import com.gaojianhui.dto.QuestionDTO;
import com.gaojianhui.mapper.QuestionMapper;
import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.Question;
import com.gaojianhui.model.QuestionExample;
import com.gaojianhui.model.User;
import com.gaojianhui.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size){
        int totalPages;
        int totalCount = (int) questionMapper.countByExample(new QuestionExample());
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
        QuestionExample questionExample = new QuestionExample();

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            /*UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            User user = users.get(0);*/
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        int totalCount = (int) questionMapper.countByExample(questionExample);
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
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Long id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdateQuestion(Question question) {
        Long id = question.getId();
        if (id == null){
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //编辑更新
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question, questionExample);
        }

    }

}

