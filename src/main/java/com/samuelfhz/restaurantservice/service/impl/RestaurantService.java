package com.samuelfhz.restaurantservice.service.impl;

import com.samuelfhz.restaurantservice.model.Restaurant;
import com.samuelfhz.restaurantservice.model.Tables;
import com.samuelfhz.restaurantservice.model.enums.RestaurantState;
import com.samuelfhz.restaurantservice.model.enums.TableState;
import com.samuelfhz.restaurantservice.repository.RestaurantRepository;
import com.samuelfhz.restaurantservice.repository.TableRepository;
import com.samuelfhz.restaurantservice.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Restaurant getRestaurant(Long id) {
        System.out.println(id);
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        if(restaurant.getTables() != null){
            for(Tables t : restaurant.getTables()){
                t.setRestaurant(restaurant);
                t.setTableState(TableState.FREE);
                tableRepository.save(t);
            }
        }

        restaurant.setRestaurantState(RestaurantState.CREATED);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant restaurantDB = getRestaurant(restaurant.getId());
        if(restaurant == null)
            return null;
        restaurantDB.setId(restaurant.getId());
        restaurantDB.setName(restaurant.getName());
        restaurantDB.setAddress(restaurant.getAddress());
        restaurantDB.setRestaurantState(restaurant.getRestaurantState());
        return restaurantRepository.save(restaurantDB);
    }

    @Override
    public Restaurant deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurant(id);
        if(restaurant == null)
            return null;
        restaurant.setRestaurantState(RestaurantState.INACTIVE);
        return restaurantRepository.save(restaurant);
    }
}
