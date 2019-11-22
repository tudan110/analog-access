package indi.tudan.analogaccess.core;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟访问页面
 *
 * @author wangtan
 * @date 2019-11-19 21:02:58
 * @since 1.0
 */
@Data
@Builder
@Slf4j
public class AnalogAccess {

    private String blogPageUrl;

    private String listUrlTemplate;

    private String pageSizeStart;

    private String listTotalStart;

    private String listTotalEnd;

    private String articleOriginalStart;

    private String articleOriginalEnd;

    private String articleIdentificationStart;

    private String articleIdentificationEnd;

    @Tolerate
    public AnalogAccess() {
    }

    /**
     * 访问
     *
     * @date 2019-11-19 21:07:03
     */
    public void process() {

        // 获取分页页面 Url
        List<String> pageUrlList = getPageUrls();

        pageUrlList.forEach(pageUrl -> {

            // 获取文章链接
            List<String> articleUrlList = getArticleUrls(pageUrl);

            // 访问文章
            articleUrlList.forEach(HttpUtil::get);
        });

    }

    /**
     * 返回分页页面链接
     *
     * @return 页面链接数组
     * @date 2019-11-22 15:15:43
     */
    private List<String> getPageUrls() {

        // 获取页面内容
        String htmlContent = HttpUtil.get(blogPageUrl);

        int pageSizeIndex = htmlContent.indexOf(pageSizeStart);
        int listTotalIndex = htmlContent.indexOf(listTotalStart);
        int pageQueryStrIndex = htmlContent.indexOf(listTotalEnd);

        String pageSizeStr = htmlContent.substring(pageSizeIndex, listTotalIndex);
        String listTotalStr = htmlContent.substring(listTotalIndex, pageQueryStrIndex);

        int pageSize = ReUtil.getFirstNumber(pageSizeStr);
        int listTotal = ReUtil.getFirstNumber(listTotalStr);
        int pageNum = listTotal / pageSize + 1;

        List<String> pageUrlList = new ArrayList<>();
        for (int i = 0; i < pageNum; i++) {

            String pageUrl = StrUtil.format(listUrlTemplate, i + 1);
            pageUrlList.add(pageUrl);

            log.info("分页页面链接 {}: {}", i + 1, pageUrl);
        }

        return pageUrlList;
    }

    /**
     * 获取文章链接
     *
     * @param pageUrl 分页页面链接
     * @return 文章链接数组
     * @date 2019-11-22 15:29:52
     */
    private List<String> getArticleUrls(String pageUrl) {

        // 文章链接数组
        List<String> articleUrlList = new ArrayList<>();

        // 获取页面内容
        String htmlContent = HttpUtil.get(pageUrl);

        // 计算当前页，有多少篇文章
        int articleNum = ReUtil.count(articleOriginalStart, htmlContent);

        for (int i = 0; i < articleNum; i++) {

            // 1、粗略匹配
            int startIndex = htmlContent.indexOf(articleOriginalStart);
            int endIndex = htmlContent.indexOf(articleOriginalEnd);

            // 得到粗略内容
            String originalStr = htmlContent.substring(startIndex, endIndex);

            // 2、精确匹配
            int articleStartIndex = originalStr.indexOf(articleIdentificationStart);
            int articleEndIndex = originalStr.indexOf(articleIdentificationEnd);

            // 精确匹配内容
            String articleUrl = originalStr.substring(articleStartIndex + 9, articleEndIndex);

            // 剔除已经匹配过的内容
            htmlContent = htmlContent.substring(endIndex + articleOriginalEnd.length());

            // 添加到待文章链接数组中
            articleUrlList.add(articleUrl);

            log.info("文章链接 {}: {}", i + 1, articleUrl);
        }

        return articleUrlList;
    }
}
