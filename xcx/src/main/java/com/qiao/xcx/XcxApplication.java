package com.qiao.xcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qiao.xcx.dao")
public class XcxApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcxApplication.class, args);
    }

}
