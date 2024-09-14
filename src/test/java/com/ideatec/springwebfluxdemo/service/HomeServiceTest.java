package com.ideatec.springwebfluxdemo.service;

import com.ideatec.springwebfluxdemo.entity.Cart;
import com.ideatec.springwebfluxdemo.entity.CartItem;
import com.ideatec.springwebfluxdemo.entity.Item;
import com.ideatec.springwebfluxdemo.repository.CartRepository;
import com.ideatec.springwebfluxdemo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HomeServiceTest {

	CartService cartService;

	@MockBean private ItemRepository itemRepository; // <2>

	@MockBean private CartRepository cartRepository; // <2>
	// end::class-under-test[]

	// tag::before[]
	@BeforeEach // <1>
	void setUp() {
		// Define test data <2>
		Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
		CartItem sampleCartItem = new CartItem(sampleItem);
		Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

		// Define mock interactions provided
		// by your collaborators <3>
		when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
		when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
		when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

		cartService = new CartService(itemRepository, cartRepository); // <4>
	}
	// end::before[]

	// tag::test[]
	@Test
	void addItemToEmptyCartShouldProduceOneCartItem() { // <1>
		cartService.addToCart("My Cart", "item1") // <2>
				.as(StepVerifier::create) // <3>
				.expectNextMatches(cart -> { // <4>
					assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) //
							.containsExactlyInAnyOrder(1); // <5>

					assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
							.containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

					return true; // <7>
				}) //
				.verifyComplete(); // <8>
	}
	// end::test[]

	// tag::test2[]
	@Test
	void alternativeWayToTest() { // <1>
		StepVerifier.create( //
						cartService.addToCart("My Cart", "item1")) //
				.expectNextMatches(cart -> { // <4>
					assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) //
							.containsExactlyInAnyOrder(1); // <5>

					assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
							.containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

					return true; // <7>
				}) //
				.verifyComplete(); // <8>
	}
	// end::test2[]
}