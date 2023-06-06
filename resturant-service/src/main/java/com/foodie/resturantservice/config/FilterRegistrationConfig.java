package com.foodie.resturantservice.config;

import com.foodie.customerservice.config.filters.PrincipalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean<PrincipalFilter> principalFilter(){
        FilterRegistrationBean<PrincipalFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new PrincipalFilter());
        filterRegistrationBean.addUrlPatterns("/api/customer/user/update");
        filterRegistrationBean.addUrlPatterns("/api/customer/user/block");
        filterRegistrationBean.addUrlPatterns("/api/customer/user/unblock");
        filterRegistrationBean.addUrlPatterns("/api/customer/user/is-customer-blocked");
        filterRegistrationBean.addUrlPatterns("/api/customer/address/**");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
