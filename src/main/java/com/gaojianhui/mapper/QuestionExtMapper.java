package com.gaojianhui.mapper;

import com.gaojianhui.model.Question;
import com.gaojianhui.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}