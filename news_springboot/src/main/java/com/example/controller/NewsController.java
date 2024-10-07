package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.Category;
import com.example.pojo.News;
import com.example.service.CategoryService;
import com.example.service.NewsService;
import com.example.utils.NewsClustering;
import com.example.utils.PullNewsSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/spider")
    public boolean spider(){
//        System.out.println("运行1");
//        PullNewsSpider spider = new PullNewsSpider();
//        HashSet<News> newsSet = spider.pullNewsSet();
//        for (News news : newsSet) {
//            if(!newsService.saveNews(news)){
//                System.out.println("失败1");
//                return false;
//            }
//        }
        HashSet<News> newsSet = new HashSet<>(newsService.list());
//        k聚类算法进行聚类
        NewsClustering newsClustering = new NewsClustering();
        HashSet<Category> categorySet = newsClustering.sort(newsSet);

//        分类数据保存到数据库category表
        for (Category category : categorySet) {
            if(!categoryService.saveNewsCategory(category)){
                System.out.println("分类失败！");
            }
        }
        return true;
    }
    @GetMapping
    public List<News> findALl(){
        return newsService.list();
    }

    @GetMapping("/page")
    public IPage<News> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String title, @RequestParam String source, @RequestParam String publishDate){
        IPage<News> page = new Page<>(pageNum,pageSize);
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();

        if(!"".equals(title)){
            queryWrapper.like("title",title);
        }
        if(!"".equals(source)) {
            queryWrapper.like("source", source);
        }
        if(publishDate != null) {
            queryWrapper.like("publish_date", publishDate);
        }
        return newsService.page(page, queryWrapper);
    }

    @PostMapping
    public boolean save(@RequestBody News news){
        return newsService.saveNews(news);
    }


//    分类搜索
    @GetMapping("/sort")
    public Map<String, Object> findNewsByCategoryId(@RequestParam Integer categoryId,@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        System.out.println("分类搜索");
//        OFFSET = (页数 - 1) * 每页条数       "LIMIT #{pageSize} OFFSET #{pageNum};"

        if(pageNum > 1){
            pageNum = (pageNum-1)*pageSize;
        }
        List<News> newsList = newsService.findNewsByCategoryId(categoryId,pageSize,pageNum);
        Long categoryCount = newsService.getNewsCategoryCount( categoryId);
        // 构建一个返回给前端的Map
        Map<String, Object> response = new HashMap<>();
        response.put("categoryCount", categoryCount);
        response.put("newsList", newsList);
        return response;
    }
}
