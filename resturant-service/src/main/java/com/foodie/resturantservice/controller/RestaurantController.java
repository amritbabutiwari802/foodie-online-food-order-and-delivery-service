package com.foodie.resturantservice.controller;

import com.foodie.resturantservice.config.Constants;
import com.foodie.resturantservice.dto.AuthRegisterRequestDto;
import com.foodie.resturantservice.dto.CreateRestaurantDto;
import com.foodie.resturantservice.entities.Restaurant;
import com.foodie.resturantservice.services.RestaurantService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;



    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/create")
    public Restaurant createRestaurant(@RequestBody() CreateRestaurantDto createRestaurantDto) {
        return this.restaurantService.createRestaurant(createRestaurantDto);
    }

    @PatchMapping("/blockRestaurant/{restaurantId}")
    public String blockRestaurant(@PathVariable("restaurantid") UUID restaurantId) {
        this.restaurantService.blockRestaurant(restaurantId);
        return "blocked";
    }

    @PatchMapping("/unblock/{restaurantid}")
    public String unblockRestaurant(@PathVariable("restaurantid") UUID restaurantId) {
      this.restaurantService.unblockRestaurant(restaurantId);
      return "unblocked";
    }
}
