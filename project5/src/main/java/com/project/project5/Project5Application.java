package com.project.project5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.project.project5.dao")
@EnableScheduling
@SpringBootApplication
public class Project5Application {

    public static void main(String[] args) {
        SpringApplication.run(Project5Application.class, args);
    }

}
