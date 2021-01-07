package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * // web容器中进行部署,继承SpringBootServletInitializer类，重写父类的configure方法
 */
@SpringBootApplication
public class LemonAdminApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LemonAdminApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(LemonAdminApplication.class, args);
    }

}
