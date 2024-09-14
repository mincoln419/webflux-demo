package com.ideatec.springwebfluxdemo.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
class ItemUnitTest {

	@Test
	public void itemBasicsShouldWork(){
		Item sampleItem = new Item("item1", "TV tray","Alf TV Tray", 19.99);

		assertThat(sampleItem.getId()).isEqualTo("item1");
		assertThat(sampleItem.getName()).isEqualTo("TV tray");
		assertThat(sampleItem.getDescription()).isEqualTo("Alf TV Tray");
		assertThat(sampleItem.getPrice()).isEqualTo(19.99);

		Item sampleItem2 = new Item("item1", "TV tray","Alf TV Tray", 19.99);
		assertThat(sampleItem).isEqualTo(sampleItem2);
	}
}