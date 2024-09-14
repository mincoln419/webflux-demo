package com.ideatec.springwebfluxdemo.entity;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.geo.Point;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private @BsonId String id;
	private String name;
	@Setter
	private double price;

	private String description;

	private String distributorRegion;
	private Date releaseDate;
	private int availableUnits;
	private Point location;
	private boolean active;

	public Item(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Item(String id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Double.compare(price, item.price) == 0 && Objects.equals(id, item.id) && Objects.equals(name, item.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
	}

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
