package com.foodie.auth.services;

import com.foodie.auth.config.GrantedAuthority;
import com.foodie.auth.config.JwtUtils;
import com.foodie.auth.dao.RestaurantAuthDao;
import com.foodie.auth.dto.*;
import com.foodie.auth.entity.CustomerAuth;
import com.foodie.auth.entity.RestaurantAuth;
import com.foodie.auth.models.JwtClaims;
import com.foodie.auth.models.UserAuthority;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RestaurantAuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RestaurantAuthDao restaurantAuthDao;

    public RestaurantAuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, RestaurantAuthDao restaurantAuthDao) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.restaurantAuthDao = restaurantAuthDao;
    }

    public String register(RestaurentAuthRegisterDto restaurentAuthRegisterDto){
        String passwordHash = passwordEncoder.encode(restaurentAuthRegisterDto.getPassword());
        RestaurantAuth restaurantAuth = new RestaurantAuth();
        restaurantAuth.setRestaurantId(restaurentAuthRegisterDto.getRestaurantId());
        restaurantAuth.setBranchId(restaurentAuthRegisterDto.getBranchId());
        restaurantAuth.setEmail(restaurentAuthRegisterDto.getEmail());
        restaurantAuth.setPassword(passwordHash);
        this.restaurantAuthDao.save(restaurantAuth);
        return "created";
    }

    public LoginResponseDto login(LoginRequestDto customerLoginRequestDto){
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(customerLoginRequestDto.getEmail(),
                        customerLoginRequestDto.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
        }
        if(!authentication.getAuthorities().contains(GrantedAuthority.RESTAURANT)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
        }
        List<RestaurantAuth> restaurantAuths = this.restaurantAuthDao.findByEmail(authentication.getPrincipal().toString());
        UUID branchId= restaurantAuths.get(0).getBranchId();
        String jwtToken = this.jwtUtils.generateJwtTokenForRestaurant(branchId.toString());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        return loginResponseDto;
    }


}
