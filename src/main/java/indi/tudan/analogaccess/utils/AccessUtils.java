package indi.tudan.analogaccess.utils;

import cn.hutool.core.util.ArrayUtil;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 模拟访问工具类
 *
 * @author wangtan
 * @date 2019-11-25 14:57:34
 * @since 2.0
 */
@Slf4j
public class AccessUtils {

    /**
     * 根据 url 获取文章链接
     *
     * @param url     待抓取链接
     * @param filters 文章链接过滤字符串
     * @date 2019-11-25 15:00:45
     */
    public static HashSet<String> getArticleUrlSet(String url, String... filters) {

        log.info("开始从 {} 中解析文章链接 ...", url);

        HashSet<String> articleUrlSet = new HashSet<>();

        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            HtmlPage htmlPage = webClient.getPage(url);
            DomNodeList domNodeList = htmlPage.getElementsByTagName("a");

            // 发现文章链接
            articleUrlSet = (HashSet<String>) domNodeList.stream()
                    .filter(p -> {
                        String href = ((DomElement) p).getAttribute("href");

                        boolean flag = true;

                        if (ArrayUtil.isNotEmpty(filters)) {
                            for (int i = 0; i < filters.length; i++) {
                                if (!href.contains(filters[i])) {
                                    flag = false;
                                    break;
                                }
                            }
                        }

                        return flag;
                    })
                    .map(q -> ((DomElement) q).getAttribute("href"))
                    .collect(Collectors.toSet());

            webClient.close();

        } catch (FailingHttpStatusCodeException | IOException e) {
            log.error("使用 htmlunit 解析网页失败。", e);
        }

        log.info("解析到 {} 篇文章。", articleUrlSet.size());
        return articleUrlSet;
    }
}
