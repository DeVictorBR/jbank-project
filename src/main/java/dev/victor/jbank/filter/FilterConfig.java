package dev.victor.jbank.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final IpFilter ipFilter;
    private final PerformanceFilter performanceFilter;

    public FilterConfig(IpFilter ipFilter, PerformanceFilter performanceFilter) {
        this.ipFilter = ipFilter;
        this.performanceFilter = performanceFilter;
    }

    @Bean
    public FilterRegistrationBean<IpFilter> ipFilterRegistrationBean() {
        FilterRegistrationBean<IpFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(ipFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<PerformanceFilter> performanceFilterRegistration() {
        FilterRegistrationBean<PerformanceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(performanceFilter);
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
