package com.example.demo;

import com.example.demo.interceptor.UserInSystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserInSystemInterceptor userInSystemInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInSystemInterceptor).addPathPatterns("/store/*","/store/**","/pet/*","/pet/**");
    }
}
