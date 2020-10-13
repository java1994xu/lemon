//package com.lemon.common.config.shiro;
//
//import com.n94.common.config.jwt.JwtFilter;
//import com.n94.common.utils.ConvertUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * 1、首先需要提供一个 Realm 的实例。
// * 2、需要配置一个 SecurityManager，在 SecurityManager 中配置 Realm。
// * 3、配置一个 ShiroFilterFactoryBean ，在 ShiroFilterFactoryBean 中指定路径拦截规则等。
// *
// * @author xubb
// * @Description shiro 配置类
// * @create 2020-06-09 17:14
// */
//@Slf4j
//@Configuration
//public class ShiroConfig {
//
//    @Value("${excludeUrls}")
//    private String excludeUrls;
//
//    @Value("${spring.redis.port}")
//    private String port;
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.password}")
//    private String redisPassword;
//
//    /**
//     * Filter Chain定义说明
//     *
//     * 1、一个URL可以配置多个Filter，使用逗号分隔
//     * 2、当设置多个过滤器时，全部验证通过，才视为通过
//     * 3、部分过滤器可指定参数，如perms，roles
//     */
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 拦截器
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        if(ConvertUtils.isNotEmpty(excludeUrls)){
//            String[] permissionUrl = excludeUrls.split(",");
//            for(String url : permissionUrl){
//                filterChainDefinitionMap.put(url,"anon");
//            }
//        }
//
//        //cas验证登录
//
//        // 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
//        filterMap.put("jwt", new JwtFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
//        filterChainDefinitionMap.put("/**", "jwt");
//
//        // 未授权界面返回JSON
//        shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/403");
//        shiroFilterFactoryBean.setLoginUrl("/sys/common/403");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 配置一个 SecurityManager，在 SecurityManager 中配置 Realm。
//     * @param myRealm
//     * @return
//     */
//    @Bean("securityManager")
//    public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myRealm);
//
//        /*
//         * 关闭shiro自带的session，详情见文档
//         * http://shiro.apache.org/session-management.html#SessionManagement-
//         * StatelessApplications%28Sessionless%29
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//        //自定义缓存实现,使用redis
//        securityManager.setCacheManager(redisCacheManager());
//        return securityManager;
//    }
//
//    /**
//     * 下面的代码是添加注解支持
//     * @return
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//
//    /**
//     * cacheManager 缓存 redis实现
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    public RedisCacheManager redisCacheManager() {
//        log.info("===============(1)创建缓存管理器RedisCacheManager");
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
//        redisCacheManager.setPrincipalIdFieldName("id");
//        //用户权限信息缓存时间
//        redisCacheManager.setExpire(200000);
//        return redisCacheManager;
//    }
//
//    /**
//     * 配置shiro redisManager
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    @Bean
//    public RedisManager redisManager() {
//        log.info("===============(2)创建RedisManager,连接Redis..URL= " + host + ":" + port);
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(ConvertUtils.getInt(port));
//        redisManager.setTimeout(0);
//        if (!StringUtils.isEmpty(redisPassword)) {
//            redisManager.setPassword(redisPassword);
//        }
//        return redisManager;
//    }
//
//}
