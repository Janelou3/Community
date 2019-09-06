package com.gaojianhui.service;

import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import com.gaojianhui.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/5 0005.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        String accountId = user.getAccountId();
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        User dbUser = users.get(0);
        if (dbUser != null){
            //更新
            user.setId(dbUser.getId());
            user.setGmtModified(System.currentTimeMillis());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(user.getId());
            userMapper.updateByExampleSelective(user, example);
        } else {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
