package com.samuelfhz.restaurantservice.service;

import com.samuelfhz.restaurantservice.model.Restaurant;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRestaurantService {

    Restaurant getRestaurant(Long id);
    List<Restaurant> findAllRestaurants();
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Restaurant restaurant);
    Restaurant deleteRestaurant(Long id);
}
