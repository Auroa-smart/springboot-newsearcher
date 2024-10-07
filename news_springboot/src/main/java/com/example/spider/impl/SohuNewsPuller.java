package com.example.spider.impl;


import com.example.pojo.News;
import com.example.spider.NewsPuller;
import com.example.spider.NewsUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SohuNewsPuller implements NewsPuller {

    @Override
    public  HashSet<News> pullNews(String url, int depth, Set<String> visitedUrls, Set<String> visitedNames) {
      // 限制递归深度，防止无限递归
        if (depth >= 2 || visitedUrls.contains(url)) {
            return new HashSet<>(); // 或者可以返回一个空列表或者 null
        }
        visitedUrls.add(url); // 将当前 URL 标记为已访问

        // 1.获取首页
        Document html= null;
        try {
            html = getHtmlFromUrl(url, false);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
        // 2.jsoup获取新闻<a>标签
        Elements newsATags = html.select("a");

        // 3.从<a>标签中抽取基本信息，封装成news
        HashSet<News> newsSet = new HashSet<>();

        for (Element a : newsATags) {
            String url2 = a.attr("href");
            if (visitedUrls.contains(url2) || visitedNames.contains(a.text())) {
                continue; // 已经访问过，跳过
            }
            visitedNames.add(a.text());
            switch (getUrlPrefix(url2)) {
                case "xchannel":
                    url2 = "https://www.sohu.com" + url2;
                    newsSet.addAll(pullNews(url2, 1+depth , visitedUrls,visitedNames)); // 合并递归结果
                    break;
                case "a":
//                    是文章
                    String title = a.text();
                    url2 = "http://www.sohu.com" + url2;
                    News n = new News();
                    n.setSource("搜狐");
                    n.setUrl(url2);
                    n.setTitle(title);
                    n.setPublishDate(new Date());
                    newsSet.add(n);
                    visitedUrls.add(url2); // 将当前 URL 标记为已访问
                    visitedNames.add(title);
                    break;
                case "xtopic":
                    url2 = "http://m.sohu.com" + url2;
                    newsSet.addAll(pullNews(url2,  1 + depth , visitedUrls,visitedNames)); // 合并递归结果
//                    visitedUrls.add(url2); // 将当前 URL 标记为已访问
                    break;
                case "http":
//                    目录
                    // Do nothing, URL is already absolute
                    newsSet.addAll(pullNews(url2, 1 + depth, visitedUrls,visitedNames)); // 合并递归结果
                    break;
                default:
                    break;
            }
        }
//        // 4.根据新闻url访问新闻，获取新闻内容
        newsSet.forEach(news -> {
//            logger.info("开始抽取搜狐新闻内容：{}", news.getUrl());
            Document newsHtml = null;
            try {
                String urlStr = news.getUrl();
                if (isValidURL(urlStr)) {
//                    System.out.println("开始抽取凤凰新闻《" + news.getTitle() + "》内容：" + urlStr);
                    newsHtml = getHtmlFromUrl(urlStr, false);
                    // 处理 newsHtml
                } else {
                    System.err.println("URL不合法：" + urlStr);
                    return;
                }
                Element newsContent = newsHtml.select("div.text").first();
                if(newsContent == null){
                    return;
                }
                String title = newsContent.select("div.text-title").select("h1").text();
//                String content = newsContent.select("article.article").text();
                Element article = newsContent.select("article.article").first(); // 获取第一个匹配的 article 元素

// 去除包含特定属性的 <p> 元素
                article.select("p[data-role=original-title]").remove();

// 获取剩余的文本内容
                String content = article.text();
                String image = NewsUtils.getImageFromContent(newsContent.toString());

                news.setTitle(title);
                news.setContent(content);
                news.setImage(image);
//                newsService.saveNews(news);
                System.out.println("抽取搜狐新闻《{}》成功！" + news.getTitle());
            } catch (Exception e) {
//                logger.error("新闻抽取失败:{}", news.getUrl());
                e.printStackTrace();
            }
        });

        return newsSet;
    }
    private boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    // 获取url的前缀
    private static String getUrlPrefix(String url) {
        if (url.startsWith("/xchannel") || url.startsWith("/20")) {
            return "xchannel";
        } else if (url.startsWith("/a")) {
            return "a";
        } else if (url.startsWith("/xtopic")) {
            return "xtopic";
        } else if (url.startsWith("https://track")) {
            return "other";
        }else if(url.startsWith("http")){
            return "http";
        }else {
            return "other";
        }
    }

}
