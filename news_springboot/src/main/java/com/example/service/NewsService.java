package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CategoryMapper;
import com.example.mapper.NewsMapper;
import com.example.pojo.Category;
import com.example.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService extends ServiceImpl<NewsMapper, News> {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    //存储新闻
    public boolean saveNews(News news){
        return saveOrUpdate(news);
    }
    public void refreshNews(News news){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        QueryWrapper<News> news2 = queryWrapper.like("title", news.getTitle());
        if(  news2 != null ){
            news.setNewsId(news.getNewsId());
        }
        this.saveNews(news);
    }

    public List<News> findNewsByCategoryId(Integer categoryId,Integer pageSize,Integer pageNum) {
        // 调用NewsMapper中的findNewsByCategoryId方法执行联表查询
        System.out.println("size"+pageSize+"num"+pageNum);
        return newsMapper.findNewsByCategoryId(categoryId,pageSize,pageNum);
    }

    public Long getNewsCategoryCount(Integer categoryId){
        QueryWrapper<Category> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("category_id",  categoryId); // 添加条件：category_id = 10
        return categoryMapper.selectCount(categoryWrapper); // 使用selectCount方法查询满足条件的记录数量

    }
}
