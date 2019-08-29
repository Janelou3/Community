package com.gaojianhui.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/8/28 0028.
 */
@Data
public class User {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;

}
