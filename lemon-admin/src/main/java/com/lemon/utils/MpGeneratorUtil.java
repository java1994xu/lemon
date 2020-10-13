package com.lemon.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

/**
 * @Description mybatisPlus生成代码工具类
 * @auther xubb
 * @create 2020-03-18 16:29
 */
public class MpGeneratorUtil {


    /**
     * 数据库类型
     */
    private final DbType dbType = DbType.MYSQL;
    /**
     * 数据库连结信息
     */
    private final String dbUrl = "jdbc:mysql://122.51.59.149:3306/frame_db?serverTimezone=GMT%2B8&characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&useAffectedRows=true";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String userName = "root";
    private final String password = "Mr.12345";
    private final String rootPath = "E:/generate/";

    /**
     * 项目名
     */
    private final String projectName = "lemon";
    /**
     * 指定包名
     */
    private final String packageName = "com.lemon";
    /**
     * controller基础类
     */
    private final String superControllerClass = packageName + ".common.BaseController";
    /**
     * entity基础类
     */
    private final String superEntityClass = packageName + ".common.BaseEntity";
    /**
     * 模块名 如果有模块名，则需在模块名前加. 例：.log
     */
    private final String moduleName = "system";


    private final String author = "xubb";
    /**
     * 指定生成的表名
     */
    private final String[] tableNames = new String[]{"sys_depart", "sys_depart_role","sys_depart_role_user"};
    /**
     * 生成文件，去掉表名的前缀sys_user==> prefix ="sys_",==>user.java
     */
    private final String prefix = "sys_";


    @Test
    public void generateCode() {
        //serviceNameStartWithI：user -> UserService, 设置成true: user -> IUserService
        //表名不为空，生成文件放在表名文件夹下
        for (String tableName : tableNames) {
            generateByTables(false, prefix, packageName, tableName);
        }

        //表名为空，生成所有表，只能按模块划分，不能精细到表名为文件夹
//        generateByTables(false, prefix, packageName, tableNames);
    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param prefix                前缀
     * @param packageName           包名
     * @param tableNames            表名--多表，用数组
     * @author Terry
     */
    private void generateByTables(boolean serviceNameStartWithI, String prefix, String packageName, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(prefix, tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }


    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param prefix                前缀
     * @param packageName           包名
     * @param tableName             单表表名
     * @author Terry
     */
    private void generateByTables(boolean serviceNameStartWithI, String prefix, String packageName, String tableName) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(prefix, tableName);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName, tableName.replace(prefix,""));
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     * @author Terry
     */
    private void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @param tableName   表名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    private PackageConfig getPackageConfig(String packageName, String tableName) {
        return new PackageConfig()
                .setParent(packageName)
                .setXml(moduleName + "." + tableName + ".mapper.xml")
                .setMapper(moduleName + "." + tableName + ".mapper")
                .setController(moduleName + "." + tableName + ".controller")
                .setEntity(moduleName + "." + tableName + ".entity")
                .setService(moduleName + "." + tableName + ".service")
                .setServiceImpl(moduleName + "." + tableName + ".service.impl");
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    private PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setXml(moduleName + ".mapper.xml")
                .setMapper(moduleName + ".mapper")
                .setController(moduleName + ".controller")
                .setEntity(moduleName + ".entity")
                .setService(moduleName + ".service")
                .setServiceImpl(moduleName + ".service.impl");
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Terry
     */
    private GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setSwagger2(true)
                .setIdType(IdType.ASSIGN_UUID)
                //作者
                .setAuthor(author)
                //设置输出路径
                .setOutputDir(getOutputDir(projectName))
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     * @param projectName 项目名
     * @return 项目路径
     * @author Terry
     */
    private String getOutputDir(String projectName) {
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        int index = path.indexOf(projectName);
        return rootPath + projectName + "/";
    }

    /**
     * 策略配置
     *
     * @param prefix     前缀
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    private StrategyConfig getStrategyConfig(String prefix, String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames)
                .setTablePrefix(prefix)
                //公共父类
//                .setSuperControllerClass(superControllerClass)
//                .setSuperEntityClass(superEntityClass)
                // 写于父类中的公共字段
//                .setSuperEntityColumns("id")
                //使用lombok
                .setEntityLombokModel(true)
                //rest风格
                .setRestControllerStyle(true);
    }

    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    private DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName(driver)
                .setTypeConvert(new ITypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        String t = fieldType.toLowerCase();
                        if (t.contains("datetime")) {
                            return DbColumnType.DATE;
                        }
                        //tinyint转换成Boolean
                        if (fieldType.toLowerCase().contains("tinyint")) {
                            return DbColumnType.INTEGER;
                        }
                        //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
                        return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
                    }
                });
    }

    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     * @author Terry
     */
    @SuppressWarnings("unused")
    private void generateByTables(String packageName, String prefix, String... tableNames) {
        generateByTables(true, packageName, prefix, tableNames);
    }
}
