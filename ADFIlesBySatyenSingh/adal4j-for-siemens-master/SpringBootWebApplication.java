package com.spring.security.aad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.spring.security.aad.auth.filter.AdalFilter;

@SpringBootApplication
public class SpringBootWebApplication {

	private @Autowired AutowireCapableBeanFactory beanFactory;

	private String authenticatedpaths = "/secure/*";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean adalFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		AdalFilter adalFilter = new AdalFilter();
		beanFactory.autowireBean(adalFilter);
		registration.setFilter(adalFilter);
		registration.addUrlPatterns(authenticatedpaths);
		registration.setOrder(1);
		return registration;
	}
}