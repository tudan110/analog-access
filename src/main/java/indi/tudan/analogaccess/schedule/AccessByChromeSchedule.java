package indi.tudan.analogaccess.schedule;

import indi.tudan.analogaccess.core.AccessByChrome;
import indi.tudan.analogaccess.core.ArticleUrlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 访问定时器
 *
 * @author wangtan
 * @date 2019-11-25 16:55:15
 * @since 2.0
 */
@Slf4j
@Component
public class AccessByChromeSchedule {

    /**
     * 文章链接解析器
     */
    @Autowired
    private ArticleUrlParser articleUrlParser;

    /**
     * 模拟 chrome 访问
     */
    @Autowired
    private AccessByChrome accessByChrome;

    /**
     * 更新文章链接 Set
     *
     * @date 2019-11-25 15:42:33
     * @since 2.0
     */
    @Scheduled(initialDelayString = "${schedule.url.initialDelayString}", fixedDelayString = "${schedule.url.fixedDelayString}")
    public void updateArticleUrlSet() {

        log.info("定时更新文章链接 Set。");
        articleUrlParser.process();
    }

    @Scheduled(initialDelayString = "${schedule.access.initialDelayString}", fixedDelayString = "${schedule.access.fixedDelayString}")
    public void access() {

        log.info("执行定时访问。");
        accessByChrome.process();
    }

}
