package com.samuelfhz.restaurantservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuelfhz.restaurantservice.model.Restaurant;
import com.samuelfhz.restaurantservice.model.enums.RestaurantState;
import com.samuelfhz.restaurantservice.service.IRestaurantService;
import com.samuelfhz.restaurantservice.service.impl.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllRestaurant() throws Exception {

        when(restaurantService.getRestaurant(1L)).thenReturn(Restaurant.builder()
                .id(1L).name("Name 1").address("Address 1").tables(new ArrayList<>())
                .restaurantState(RestaurantState.CREATED).build());

        mockMvc.perform(get("/restaurants/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Name 1"))
                .andExpect(jsonPath("$.address").value("Address 1"));

        verify(restaurantService).getRestaurant(1L);
    }

    @Test
    void createRestaurant() throws Exception{
        Restaurant restaurant = Restaurant.builder()
                .id(1L).name("Name 1").address("Address 1")
                .tables(new ArrayList<>()).restaurantState(RestaurantState.CREATED)
                .build();

        mockMvc
                .perform(post("/restaurants")
                        .content(objectMapper.writeValueAsString(restaurant))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

    }
}
