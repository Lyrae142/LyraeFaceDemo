package com.Lyrae.facedemo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @Author:Lyrae
 */
@Configuration
@EnableAutoConfiguration
public class ApplicationConfig {

    /**
     * 配置FreeMarker视图解析器
     * @return
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        //设置freemarker模板前缀
        resolver.setPrefix("");
        //设置freemarker模板后缀
        resolver.setSuffix(".ftl");
        //编码方式
        resolver.setContentType("text/html;charset=UTF-8");
        //.ftl中获取contextPath，在ftl能够用${request.contextPath}
        resolver.setRequestContextAttribute("request");
        //将Spring容器中的Bean作为请求属性暴露给视图页面
        resolver.setExposeContextBeansAsAttributes(true);
        //设置HttpServletRequest的attributes是否可以覆盖Controller的model 的attributes
        resolver.setExposeRequestAttributes(true);
        //所有HttpSession的属性设置是否应该加入到模型之前，与模板合并
        resolver.setExposeSessionAttributes(true);
        return resolver;
    }
}
