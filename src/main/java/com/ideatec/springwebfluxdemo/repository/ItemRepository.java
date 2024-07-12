package com.ideatec.springwebfluxdemo.repository;

import com.ideatec.springwebfluxdemo.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
