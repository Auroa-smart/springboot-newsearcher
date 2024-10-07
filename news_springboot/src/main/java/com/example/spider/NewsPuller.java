package com.example.spider;

import com.example.pojo.News;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;

//爬虫接口
//NewsPuller 接口有一个抽象方法 pullNews 用于拉取新闻，有一个默认方法用于获取新闻首页：
public interface NewsPuller {
    HashSet<News>  pullNews(String url, int depth, Set<String> visitedUrls, Set<String> visitedNames);
    default Document getHtmlFromUrl(String url, boolean useHtmlUnit) throws Exception {
        try {
            if (!useHtmlUnit) {
                return Jsoup.connect(url)//Jsoup连接到指定url并模拟火狐浏览器的用户代理获取页面HTML内容
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:102.0) Gecko/20100101 Firefox/102.0")
                        .get();
            } else {//创建一个HtmlUnit的WebClient对象，并设置特定的配置选项，包括启用JavaScript、禁用CSS以及处理异常。
                WebClient webClient = new WebClient(BrowserVersion.CHROME);
                webClient.getOptions().setJavaScriptEnabled(true);
                webClient.getOptions().setCssEnabled(false);
                webClient.getOptions().setActiveXNative(false);
                webClient.getOptions().setCssEnabled(false);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
                webClient.getOptions().setTimeout(10000);
                HtmlPage rootPage = null;
                try {//使用HtmlUnit从URL获取HTML内容。它等待后台JavaScript的执行，然后将页面内容转换为HTML字符串。最后，使用Jsoup解析HTML字符
                    rootPage = webClient.getPage(url);
                    webClient.waitForBackgroundJavaScript(10000);
                    String htmlString = rootPage.asXml();
                    return Jsoup.parse(htmlString);
                } catch (Exception e) {
                    throw e;
                } finally {
                    webClient.close();
                }
            }
        } catch (Exception e) {
            // 如果获取页面失败，返回一个空的 Document 对象
            return new Document("");
        }
    }
}