package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@Mapper
public interface NewsMapper extends BaseMapper<News> {


    @Select("SELECT n.* FROM news n " +
            "JOIN news_category nc ON n.news_id = nc.news_id " +
            "WHERE nc.category_id = #{categoryId} "+
            "LIMIT #{pageSize} OFFSET #{pageNum};"
    )
    List<News> findNewsByCategoryId(@Param("categoryId") Integer categoryId, @RequestParam Integer pageSize, @RequestParam Integer pageNum);


}
