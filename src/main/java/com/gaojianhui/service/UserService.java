package com.gaojianhui.service;

import com.gaojianhui.mapper.UserMapper;
import com.gaojianhui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/9/5 0005.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        String accountId = user.getAccountId();
        User dbUser = userMapper.findUserByAccountId(accountId);
        if (dbUser != null){
            //更新
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateUser(user);
        } else {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }
    }
}
