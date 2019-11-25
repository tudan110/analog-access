package indi.tudan.analogaccess.core;

import cn.hutool.http.HttpUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * 模拟 chrome 访问
 *
 * @author wangtan
 * @date 2019-11-25 15:50:05
 * @since 2.0
 */
@Data
@Builder
@Slf4j
@Component
public class AccessByChrome {

    /**
     * 文章链接解析器
     */
    @Autowired
    private ArticleUrlParser articleUrlParser;

    @Tolerate
    public AccessByChrome() {
    }

    /**
     * 访问 url
     *
     * @date 2019-11-25 15:52:38
     */
    public void process() {

        HashSet<String> articleUrlSet = articleUrlParser.getArticleUrlSet();

        log.info("博主当前有 {} 篇文章，开始模拟访问 ...", articleUrlSet.size());
        articleUrlSet.forEach(HttpUtil::get);
    }
}
