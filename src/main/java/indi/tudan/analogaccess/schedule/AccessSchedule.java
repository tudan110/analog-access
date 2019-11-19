package indi.tudan.analogaccess.schedule;

import indi.tudan.analogaccess.core.AnalogAccess;
import lombok.extern.slf4j.Slf4j;
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

    @Scheduled(initialDelayString = "${schedule.initialDelayString}",fixedDelayString = "${schedule.fixedDelayString}")
    public void access() {

        log.info("执行定时访问。");
//        AnalogAccess.builder().build().process();
    }
}
