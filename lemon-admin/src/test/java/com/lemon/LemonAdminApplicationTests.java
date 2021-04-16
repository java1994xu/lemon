package com.lemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class LemonAdminApplicationTests {

    @Test
    void contextLoads() {

        String str = "http://58.210.227.103:8083/DocPath/202007/8e5aa6b7-8ed4-48a4-80fd-fdd558533710.xls|非现场交通违法处罚点（2020年7月10日更新）.xls";
        String substring = str.substring(0, str.indexOf("|"));
        String sub = str.substring(str.indexOf("|")+1,str.length());
        System.out.println(substring);
        System.out.println(sub);
        String substring1 = str.substring(str.lastIndexOf("/") + 1);
        System.out.println(substring1);


    }


    @Test
    public void getSpringVersion() {
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }

}
