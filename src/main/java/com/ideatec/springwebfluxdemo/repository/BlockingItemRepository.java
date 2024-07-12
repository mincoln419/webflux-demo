package com.ideatec.springwebfluxdemo.repository;

import com.ideatec.springwebfluxdemo.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockingItemRepository extends CrudRepository<Item, String> {

}

