package com.ideatec.springwebfluxdemo.controller;

import com.ideatec.springwebfluxdemo.entity.Dish;
import com.ideatec.springwebfluxdemo.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenservice){
        this.kitchenService = kitchenservice;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> servDishes(){
        return this.kitchenService.getDishes();

    }
    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliveredDishes(){
        return this.kitchenService.getDishes()
                .map(dish -> Dish.deliver(dish));
    }
}
