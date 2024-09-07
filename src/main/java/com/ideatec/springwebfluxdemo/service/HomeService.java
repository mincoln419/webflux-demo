package com.ideatec.springwebfluxdemo.service;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.repository.CartRepository;
import com.ideatec.springwebfluxdemo.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;

@Service
public class HomeService {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;

	public HomeService(ItemRepository itemRepository, // <2>
					   CartRepository cartRepository) {
		this.itemRepository = itemRepository;
		this.cartRepository = cartRepository;
	}

	public Rendering getRenderingData(Rendering.Builder<?> builder){

		return builder
				.modelAttribute("items", this.itemRepository.findAll()
						.log("item repo fild all")
				)
				.modelAttribute("cart", this.cartRepository.findById("My Cart")
						.defaultIfEmpty(new Cart("My Cart")).log("cart find"))

				.build();
	}

	public Flux<Item> searchByExample(String name, String description, boolean useAnd){
		Item item = new Item(name, description, 0.0);

		ExampleMatcher matcher = (useAnd ?ExampleMatcher.matchingAll()
				: ExampleMatcher.matchingAny())
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				.withIgnoreCase()
				.withIgnoreCase("price");

		Example<Item> probe = Example.of(item, matcher);
		return itemRepository.findAll(probe);


	}
}
