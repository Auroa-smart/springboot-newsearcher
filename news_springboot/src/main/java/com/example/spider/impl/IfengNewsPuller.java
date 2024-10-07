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


public class IfengNewsPuller implements NewsPuller {

    private String url;
    //    @Autowired
//    private NewsService newsService;
    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public HashSet<News>  pullNews(String url, int depth, Set<String> visitedUrls, Set<String> visitedNames) {
//        List<News> newsList = new ArrayList<>();
        HashSet<News> newsSetResult = new HashSet<>();
//        logger.info();
//        System.out.println("开始拉取凤凰新闻！");
        // 1.获取首页
        Document html= null;
        try {
            html = getHtmlFromUrl(url, false);
        } catch (Exception e) {
//            logger.error();
//            System.out.println("==============获取凤凰首页失败: {"+url+" =============" );
            e.printStackTrace();
            return null;
        }
//        System.out.println(html);
        // 2.jsoup获取新闻<a>标签
        Elements newsATags = html.select("a");
//                  .select("div#headLineDefault")
//                .select("ul.FNewMTopLis")
//                .select("li")
//                .select("a");
//        System.out.println(newsATags);
//        System.out.println(html);
//        System.out.println(html.select("li"));
//        System.out.println(html.select("div#headLineDefault"));
//        System.out.println(html.select("ul.FNewMTopLis"));
//         3.从<a>标签中抽取基本信息，封装成news
        HashSet<News> newsSet = new HashSet<>();
        for (Element a : newsATags) {
            url = a.attr("href");
            String title = a.text();
            News n = new News();
            n.setSource("凤凰");
            n.setUrl(url);
            n.setTitle(title);
            n.setPublishDate(new Date());
            newsSet.add(n);
        }
        // 4.根据新闻url访问新闻，获取新闻内容
        newsSet.parallelStream().forEach(news -> {
//            logger.info();
//            System.out.println("开始抽取凤凰新闻《"+news.getTitle()+"》内容："+news.getUrl());
            Document newsHtml = null;
            try {
//                newsHtml = getHtmlFromUrl(news.getUrl(), false);
                String urlStr = news.getUrl();
                if (isValidURL(urlStr)) {
//                    System.out.println("开始抽取凤凰新闻《" + news.getTitle() + "》内容：" + urlStr);
                    newsHtml = getHtmlFromUrl(urlStr, false);
                    // 处理 newsHtml
                } else {
//                    System.err.println("URL不合法：" + urlStr);
                    return;
                }

//                <div class="index_text_D0U1y">
                Elements contentElement = newsHtml.select("div.index_text_D0U1y");

                if (contentElement.isEmpty()) {
                    contentElement = newsHtml.select("div#yc_con_txt");
                }

                if (contentElement.isEmpty())
                    return;

                String contentHtml = contentElement.toString();
                String content = contentElement.text();

                String image = NewsUtils.getImageFromContent(contentHtml);
                news.setContent(content);
                news.setImage(image);
//                newsService.saveNews(news);
//                NewsExample newsExample = new NewsExample();
//                newsExample.createCriteria().andUrlEqualTo(news.getUrl());

                newsSetResult.add(news);
//                newsList.add(news);
                System.out.println("抽取凤凰新闻《"+news.getTitle()+"》成功！" );

            } catch (Exception e) {
//                logger.error();
//                System.out.println("凤凰新闻抽取失败:"+news.getUrl() );
                e.printStackTrace();
            }
        });

//        logger.info();
//        System.out.println("凤凰新闻抽取完成！");
        return newsSetResult;
    }
    // 检查URL是否合法的函数
    private boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}

