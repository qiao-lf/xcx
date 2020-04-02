package com.qiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qiao.mapper")
public class MyxcxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyxcxApplication.class, args);
    }

}
