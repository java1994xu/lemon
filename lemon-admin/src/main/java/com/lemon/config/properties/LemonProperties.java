package com.lemon.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xubb
 * @Description
 * @create 2021-01-06 0:51
 */
@Configuration
@ConfigurationProperties(prefix = "lemon")
public class LemonProperties {
    private String fileRootPath;

    public String getFileRootPath() {
        return fileRootPath;
    }

    public void setFileRootPath(String fileRootPath) {
        this.fileRootPath = fileRootPath;
    }
}
