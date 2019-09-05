package com.gaojianhui.model;

import lombok.Data;

/**
 * Created by GJH on 2019/8/28.
 */
@Data
public class Question {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;

}
