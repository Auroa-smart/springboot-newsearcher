package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@EnableScheduling //启动定时任务！！
public class NewsSpingbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsSpingbootApplication.class, args);
    }
}
