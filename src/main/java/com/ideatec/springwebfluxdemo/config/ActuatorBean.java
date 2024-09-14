package com.ideatec.springwebfluxdemo.config;

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ActuatorBean {

	@Bean
	public InMemoryHttpExchangeRepository traceRepository(){
		return new InMemoryHttpExchangeRepository();
	}
}
