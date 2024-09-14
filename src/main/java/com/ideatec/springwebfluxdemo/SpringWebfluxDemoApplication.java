package com.ideatec.springwebfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.engine.TemplateManager;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveView;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringWebfluxDemoApplication {

	public static void main(String[] args) {

		BlockHound.install();
		SpringApplication.run(SpringWebfluxDemoApplication.class, args);
	}

}
