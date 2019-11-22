package indi.tudan.analogaccess.schedule;

import indi.tudan.analogaccess.core.AnalogAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 访问定时器
 *
 * @author wangtan
 * @date 2019-11-19 21:10:20
 * @since 1.0
 */
@Slf4j
@Component
public class AccessSchedule {

    @Value("${csdn.blogPage.url}")
    private String blogPageUrl;

    @Value("${csdn.blogPage.listUrlTemplate}")
    private String listUrlTemplate;

    @Value("${csdn.blogPage.pageSizeStart}")
    private String pageSizeStart;

    @Value("${csdn.blogPage.listTotalStart}")
    private String listTotalStart;

    @Value("${csdn.blogPage.listTotalEnd}")
    private String listTotalEnd;

    @Value("${csdn.article.original.start}")
    private String articleOriginalStart;

    @Value("${csdn.article.original.end}")
    private String articleOriginalEnd;

    @Value("${csdn.article.identification.start}")
    private String articleIdentificationStart;

    @Value("${csdn.article.identification.end}")
    private String articleIdentificationEnd;

    @Scheduled(initialDelayString = "${schedule.initialDelayString}", fixedDelayString = "${schedule.fixedDelayString}")
    public void access() {

        log.info("执行定时访问。");
        AnalogAccess.builder()
                .blogPageUrl(blogPageUrl)
                .listUrlTemplate(listUrlTemplate)
                .pageSizeStart(pageSizeStart)
                .listTotalStart(listTotalStart)
                .listTotalEnd(listTotalEnd)
                .articleOriginalStart(articleOriginalStart)
                .articleOriginalEnd(articleOriginalEnd)
                .articleIdentificationStart(articleIdentificationStart)
                .articleIdentificationEnd(articleIdentificationEnd)
                .build()
                .process();
    }
}
