package com.gaojianhui.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJH on 2019/9/2.
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS = new ArrayList<>();
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPages;

    public void setPagination(Integer totalPages, Integer page, Integer size){
        this.page = page;

        this.totalPages = totalPages;

        //显示当前页码数，最多七个 如：第一页1234567 第二页1234567 第三页1234567 第四页1234567 第五页2345678 ...
        pages.add(page);
        for(int i=1; i<=3; i++){
            if (page - i > 0){
                pages.add(0,page-i);  //向位置为0地方储存数据，原先list表中的数据往后移一位
            }
            if (page + i <= totalPages){
                pages.add(page + i);
            }
        }

        //是否显示上一页按钮
        if (page == 1){
            showPrevious = false;
        } else{
            showPrevious = true;
        }

        //是否显示下一页按钮
        if (page == totalPages){
            showNext = false;
        } else {
            showNext = true;
        }

        //是否显示首页按钮
        if (pages.contains(1)){
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否显示最后一页按钮
        if (pages.contains(totalPages)){
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }


}
