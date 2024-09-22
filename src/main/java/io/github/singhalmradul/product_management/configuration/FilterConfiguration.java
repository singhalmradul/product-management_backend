package io.github.singhalmradul.product_management.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.singhalmradul.product_management.filters.LoggingFilter;

@Configuration
public class FilterConfiguration {

    @Bean
    FilterRegistrationBean<LoggingFilter> loggingFilter() {
        var registrationBean = new FilterRegistrationBean<LoggingFilter>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
