package com.samuelfhz.restaurantservice.controller;

import com.samuelfhz.restaurantservice.model.Restaurant;
import com.samuelfhz.restaurantservice.service.IRestaurantService;
import com.samuelfhz.restaurantservice.service.impl.RestaurantService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return restaurant == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.findAllRestaurants();
        return restaurants.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(restaurants);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant restaurantCreated = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") Long id, @RequestBody Restaurant restaurant){
        restaurant.setId(id);
        Restaurant restaurantDB = restaurantService.updateRestaurant(restaurant);
        return restaurantDB == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("id") Long id){
        Restaurant restaurantDeleted = restaurantService.deleteRestaurant(id);
        return restaurantDeleted == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(restaurantDeleted);

    }
}
