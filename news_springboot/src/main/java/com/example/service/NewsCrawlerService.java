package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pojo.Category;
import com.example.pojo.News;
import com.example.utils.NewsClustering;
import com.example.utils.PullNewsSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class NewsCrawlerService{
    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;

    @Scheduled(cron = "0 0 0 * * *") // 定时任务，每天执行一次，可以根据需求修改cron表达式
    public void updateNewsData() {
        // 编写爬虫逻辑，抓取新闻数据并更新网站
        PullNewsSpider spider = new PullNewsSpider();
        HashSet<News> newsSet = spider.pullNewsSet();
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
//        更新到数据库
        for (News news : newsSet) {
            newsService.refreshNews(news);
        }

        newsSet = new HashSet<>(newsService.list());
//        k聚类算法进行聚类
        NewsClustering newsClustering = new NewsClustering();
        HashSet<Category> categorySet = newsClustering.sort(newsSet);

//        分类数据保存到数据库category表
        for (Category category : categorySet) {
            if(!categoryService.saveNewsCategory(category)){
                System.out.println("分类失败！");
            }
        }
    }
}
