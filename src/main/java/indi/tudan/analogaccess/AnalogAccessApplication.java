package indi.tudan.analogaccess;

import indi.tudan.analogaccess.core.AnalogAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnalogAccessApplication {

    public static void main(String[] args) {

        SpringApplication.run(AnalogAccessApplication.class, args);

//        AnalogAccess.builder().build().process();
    }

}
