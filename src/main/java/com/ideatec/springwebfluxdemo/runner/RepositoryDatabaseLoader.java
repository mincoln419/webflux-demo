package com.ideatec.springwebfluxdemo.runner;

import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class RepositoryDatabaseLoader {

	@Bean
	CommandLineRunner initialize(MongoOperations mongo){

//		Mono.delay(Duration.ofSeconds(1))
//				.doOnNext(it -> {
//					try {
//						Thread.sleep(10);
//					}
//					catch (InterruptedException e) {
//						throw new RuntimeException(e);
//					}
//				})
//				.block();
		return args -> {
			mongo.save(new Item("Alf alarm clock","good clock" , 19.99));
			mongo.save(new Item("Smurf TV tray", "good tv tray", 24.99));
		};
	}


}
