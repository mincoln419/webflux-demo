package com.ideatec.springwebfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringWebfluxDemoApplication {

	public static void main(String[] args) {

		BlockHound.install();
		SpringApplication.run(SpringWebfluxDemoApplication.class, args);
	}

}
