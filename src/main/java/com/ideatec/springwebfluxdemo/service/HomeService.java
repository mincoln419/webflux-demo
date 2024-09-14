package com.ideatec.springwebfluxdemo.service;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.repository.CartRepository;
import com.ideatec.springwebfluxdemo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class HomeService {

	private final CartService cartService;

	public Flux<Item> searchByExample(String name, String description, boolean useAnd){
		Item item = new Item(name, description, 0.0);

		ExampleMatcher matcher = (useAnd ?ExampleMatcher.matchingAll()
				: ExampleMatcher.matchingAny())
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				.withIgnoreCase()
				.withIgnoreCase("price");

		Example<Item> probe = Example.of(item, matcher);
		return cartService.getInventory();


	}
}
