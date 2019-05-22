package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;

@SpringBootApplication
@MapperScan("gen.example.mapper")
public class ScaffoldingSingleHapenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldingSingleHapenApplication.class, args);
    }

}
