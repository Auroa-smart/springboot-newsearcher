package com.example.spider.impl;


import com.example.pojo.News;
import com.example.spider.NewsPuller;
import com.example.spider.NewsUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NetEasyNewsPuller implements NewsPuller {
    @Override
    public HashSet<News> pullNews(String url, int depth, Set<String> visitedUrls, Set<String> visitedNames) {
        // 限制递归深度，防止无限递归
        if (depth >= 2 || visitedUrls.contains(url)) {
            return new HashSet<>();
        }
        visitedUrls.add(url); // 将当前 URL 标记为已访问
        // 1.获取首页
        Document html= null;
        try {
            html = getHtmlFromUrl(url, false);
        } catch (Exception e) {
//            logger.error("==============获取网易新闻首页失败: {}=============", url);
            e.printStackTrace();
            return new HashSet<>();
        }
        // 2.jsoup获取指定标签
        Elements newsA =html.select("a");
        // 3.从标签中抽取信息，封装成news
        HashSet<News> newsSet = new HashSet<>();
        newsA.forEach(a -> {
            String  url2 = a.attr("href");
            if(url2.matches("https://www\\.163\\.com/\\w+/article/.*")){
                News n = new News();
                n.setSource("网易");
                n.setUrl(url2);
                n.setPublishDate(new Date());
                newsSet.add(n);
            }
        });
        Iterator<News> iterator = newsSet.iterator();
        // 4.根据url访问新闻，获取新闻内容
        while (iterator.hasNext()) {
            News news = iterator.next();
            Document newsHtml = null;
            try {
                newsHtml = getHtmlFromUrl(news.getUrl(), false);

                Elements newsContent = newsHtml.select("div.post_body");
                if (newsContent.size() == 0) {
                    iterator.remove();
                    continue; //为0说明404没找到
                }
                Elements titleP = newsContent.select("p.otitle");
                String title = "";
                if (titleP.size() == 0) {
                    titleP = newsHtml.select("h1.post_title");
                    title = titleP.text();
                } else {
                    title = titleP.text();
                    title = title.substring(5, title.length() - 1);
                }

                String image = NewsUtils.getImageFromContent(newsContent.toString());

                news.setTitle(title);
                news.setContent(newsContent.text());
                news.setImage(image);
                System.out.println("抽取网易新闻《{}》成功！"+ news.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      return  newsSet;
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
        } else if (url.startsWith("https://www.163.com/dy/article/") || url.startsWith("https://www.163.com/news/article/")) {
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
