package com.gaojianhui.mapper;

import com.gaojianhui.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by GJH on 2019/8/28.
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")  Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Long userId,@Param(value = "offset") Integer offset, @Param(value = "size")  Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(Long userId);

    @Select("select * from question where id= #{id}")
    Question getQuestionById(Long id);

    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmtModified},tag = #{tag} where id = #{id}")
    void updateQuestion(Question question);
}
