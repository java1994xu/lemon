package com.lemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * // web容器中进行部署,继承SpringBootServletInitializer类，重写父类的configure方法
 *
 * @author xubb
 */
@SpringBootApplication
@Slf4j
public class LemonAdminApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LemonAdminApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(LemonAdminApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        path = StringUtils.isEmpty(path) ? "" : path;
        log.info("\n----------------------------------------------------------\n\t"
                + "Application Lemon is running! Access URLs:\n\t"
                + "Local: \t\thttp://localhost:" + port + path + "/\n\t"
                + "External: \thttp://" + ip + ":" + port + path + "/\n\t"
                + "swagger-ui: http://" + ip + ":" + port + path + "/swagger-ui.html\n\t"
                + "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n"
                + "----------------------------------------------------------");
    }

}
