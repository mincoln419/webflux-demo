package com.ideatec.springwebfluxdemo.repository;

import com.ideatec.springwebfluxdemo.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
