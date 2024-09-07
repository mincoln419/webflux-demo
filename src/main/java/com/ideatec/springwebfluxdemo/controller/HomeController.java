package com.ideatec.springwebfluxdemo.controller;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.CartItem;
import com.ideatec.springwebfluxdemo.repository.CartRepository;
import com.ideatec.springwebfluxdemo.repository.ItemRepository;
import com.ideatec.springwebfluxdemo.service.CartService;
import com.ideatec.springwebfluxdemo.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

	private final CartService cartService;
	private final HomeService homeService;

	public HomeController(CartService cartService, HomeService homeService) {
		this.cartService = cartService;
		this.homeService = homeService;
	}

	@GetMapping
	public Mono<Rendering> home() {


		return Mono.just(homeService.getRenderingData(Rendering.view("home.html")));
	}

	@PostMapping("/add/{id}")
	Mono<String> addToCart(@PathVariable String id) {
		return cartService.addToCart(id)
				.thenReturn("redirect:/");
	}

	@GetMapping("/search")
	public Mono<Rendering> search(@RequestParam(required = false) String name,
								  @RequestParam(required = false) String description,
								  @RequestParam(required = false) boolean useAnd) {
		return Mono.just(Rendering.view("home.html")
				.modelAttribute("items", homeService.searchByExample(name, description, useAnd))
				.modelAttribute("cart", this.cartService.findById("My Cart")
						.defaultIfEmpty(new Cart("My Cart"))).build()
		);
	}
}
