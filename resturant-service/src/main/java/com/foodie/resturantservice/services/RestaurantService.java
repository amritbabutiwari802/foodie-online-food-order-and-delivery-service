package com.foodie.resturantservice.services;

import com.foodie.resturantservice.config.Constants;
import com.foodie.resturantservice.dto.AuthRegisterRequestDto;
import com.foodie.resturantservice.dto.CreateRestaurantDto;
import com.foodie.resturantservice.entities.Restaurant;
import com.foodie.resturantservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Value("${foodie.internal.secret}")
    private String internalSecret;


    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        if (this.restaurantRepository.isAlreadyRegistrated(createRestaurantDto.getRegistrationNumber())) {
            //handle already registrated
            return null;
        }

        Restaurant restaurant = this.restaurantRepository.createRestaurant(createRestaurantDto);
        AuthRegisterRequestDto authRegisterRequestDto = new AuthRegisterRequestDto();
        authRegisterRequestDto.setRestaurantId(restaurant.getId());
        authRegisterRequestDto.setEmail(createRestaurantDto.getEmail());
        authRegisterRequestDto.setPassword(createRestaurantDto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.INTERNAL_SECRET, internalSecret);
        HttpEntity<AuthRegisterRequestDto> httpEntity = new HttpEntity<>(authRegisterRequestDto, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("lb://AUTH-SERVICE/api/auth/restaurant/register", HttpMethod.POST, httpEntity, String.class);
        return restaurant;
    }

    public void blockRestaurant(UUID restaurantId) {
        this.restaurantRepository.blockRestaurant(restaurantId);
    }

    public void unblockRestaurant(UUID restaurantId) {
        this.restaurantRepository.unBlockedRestaurant(restaurantId);
    }

    public boolean isRestaurantPermittedToServe(UUID restaurantId) {
        boolean isRestaurantValid = this.restaurantRepository.isRestaurantIdValid(restaurantId);
        if (!isRestaurantValid) {
            return false;
        }
        boolean isRestaurentBlocked = this.restaurantRepository.isRestaurantBlocked(restaurantId);
        if (isRestaurentBlocked) {
            return false;
        }
        return true;
    }

}
