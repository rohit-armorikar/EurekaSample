package com.sfg8qv.ApplicationGatewayZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
public class ApplicationGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGatewayZuulApplication.class, args);
	}
	
	@Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }
}

