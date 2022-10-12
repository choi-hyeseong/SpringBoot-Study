package com.division.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringBootStudyApplication {
    //Spring Main
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}
