package com.ideatec.springwebfluxdemo.runner;

import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

	@Bean
	CommandLineRunner initialize(BlockingItemRepository repository){
		return args -> {
			repository.save(new Item("Alf alarm clock", 19.99));
			repository.save(new Item("Smurf TV tray", 24.99));
		};
	}
}
