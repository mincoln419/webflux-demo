package com.ideatec.springwebfluxdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
public class CartItem {
	private Item item;
	private int quantity;

	private CartItem(){}

	public CartItem(Item item){
		this.item = item;
		this.quantity = 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartItem cartItem = (CartItem) o;
		return quantity == cartItem.quantity && Objects.equals(item, cartItem.item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(item, quantity);
	}

	@Override
	public String toString() {
		return "CartItem{" +
				"item=" + item.toString() +
				", quantity=" + quantity +
				'}';
	}

	public void increment() {
		this.quantity++;
	}
}
