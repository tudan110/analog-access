package indi.tudan.analogaccess.core;

import indi.tudan.analogaccess.utils.AccessUtils;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;

/**
 * 文章链接解析器
 *
 * @author wangtan
 * @date 2019-11-25 15:28:04
 * @since 2.0
 */
@Data
@Builder
@Slf4j
@Component
public class ArticleUrlParser {

    @Value("${csdn.blogPage.url}")
    private String blogPageUrl;

    @Value("${csdn.article.url.filter}")
    private String[] filters;

    /**
     * 文章链接 Set
     */
    private HashSet<String> articleUrlSet;

    @Tolerate
    public ArticleUrlParser() {
    }

    /**
     * 初始化 Set 池
     */
    @PostConstruct
    public void init() {
        articleUrlSet = new HashSet<>();
    }

    /**
     * 更新文章链接 Set
     *
     * @date 2019-11-25 15:30:25
     */
    public void process() {

        long start = System.currentTimeMillis();

        // 将解析到的链接添加到 Set
        articleUrlSet.addAll(AccessUtils.getArticleUrlSet(blogPageUrl, filters));

        log.info("解析文章链接耗时: {} 毫秒。", System.currentTimeMillis() - start);
    }
}
