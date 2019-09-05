package com.gaojianhui.dto;

import com.gaojianhui.model.User;
import lombok.Data;

/**
 * Created by Administrator on 2019/8/29 0029.
 */
@Data
public class QuestionDTO {
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
    private User user;
}
