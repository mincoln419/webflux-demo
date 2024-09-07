package com.ideatec.springwebfluxdemo.service;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.CartItem;
import com.ideatec.springwebfluxdemo.repository.CartRepository;
import com.ideatec.springwebfluxdemo.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Service
public class CartService {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;

	public CartService(ItemRepository itemRepository, // <2>
					   CartRepository cartRepository) {
		this.itemRepository = itemRepository;
		this.cartRepository = cartRepository;
	}


	public Mono<Cart> addToCart(@PathVariable String id) {
		return this.cartRepository.findById("My Cart")
				.defaultIfEmpty(new Cart("My Cart"))
				.flatMap(cart -> cart.getCartItems().stream()
						.filter(cartItem -> cartItem.getItem().getId().equals(id))
						.findAny()
						.map(cartItem -> {
							cartItem.increment();
							return Mono.just(cart);
						})
						.orElseGet(() -> {
							return this.itemRepository.findById(id)
									.map(CartItem::new)
									.map(cartItem -> {
										cart.getCartItems().add(cartItem);
										return cart;
									});
						})).flatMap(this.cartRepository::save);

	}

	public Mono<Cart> findById(String id){
		return cartRepository.findById(id);
	}
}