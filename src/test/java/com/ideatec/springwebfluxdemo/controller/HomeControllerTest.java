package com.ideatec.springwebfluxdemo.controller;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.service.CartService;
import com.ideatec.springwebfluxdemo.service.HomeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(HomeController.class)
class HomeControllerTest {

	@Autowired
	private WebTestClient client;

	@MockBean
	private HomeService homeService;

	@MockBean
	private CartService cartService;

	@Test
	public void homePage() {
		when(cartService.getInventory()).thenReturn(Flux.just(
				new Item("id1", "name1", "desc1", 1.99),
				new Item("id2", "name2", "desc2", 9.99)
		));
		when(cartService.getCart("My Cart")).thenReturn(Mono.just(new Cart("My Cart")));

		client.get().uri("/").exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.consumeWith(exchangeResult -> {
							assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id1\"");
							assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id2\"");
						}

				);

	}

}