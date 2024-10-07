package com.example.utils;


import com.example.pojo.News;
import com.example.spider.impl.IfengNewsPuller;
import com.example.spider.impl.NetEasyNewsPuller;
import com.example.spider.impl.SohuNewsPuller;
import java.util.HashSet;
import java.util.Set;
public class PullNewsSpider {
    //凤凰 搜狐 网易新闻
    public static String[] urls = {"https://www.ifeng.com/","http://news.sohu.com/","http://news.163.com/rank/"};

    public HashSet<News> pullNewsSet(){
        IfengNewsPuller newsPuller1 = new IfengNewsPuller();
        HashSet<News> newsSet1 = newsPuller1.pullNews(urls[0],1,null,null );

        SohuNewsPuller newsPuller2 = new SohuNewsPuller();
        Set<String> visitedUrls1 = new HashSet<>();
        Set<String> visitedNames1 = new HashSet<>();
        HashSet<News>  newsSet2 = newsPuller2.pullNews(urls[1],1, visitedUrls1,visitedNames1 );

        NetEasyNewsPuller newsPuller = new NetEasyNewsPuller();
       Set<String> visitedUrls2 = new HashSet<>();
       Set<String> visitedNames2 = new HashSet<>();

        HashSet<News>  newsSet3 = newsPuller.pullNews(urls[2],1, visitedUrls2,visitedNames2 );

        //合并三个hashset
        HashSet<News> mergedNewsSet = new HashSet<>();
        mergedNewsSet.addAll(newsSet1);
        mergedNewsSet.addAll(newsSet2);
        mergedNewsSet.addAll(newsSet3);
        return mergedNewsSet;
    }
}
