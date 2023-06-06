package com.foodie.auth.services;

import com.foodie.auth.config.Constants;
import com.foodie.auth.config.GrantedAuthority;
import com.foodie.auth.config.JwtUtils;
import com.foodie.auth.dto.LoginRequestDto;
import com.foodie.auth.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeliveryAuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public DeliveryAuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    public LoginResponseDto login(LoginRequestDto customerLoginRequestDto) {
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(customerLoginRequestDto.getEmail(),
                        customerLoginRequestDto.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        if (!authentication.getAuthorities().contains(GrantedAuthority.RESTAURANT)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        String jwtToken = this.jwtUtils.generateJwtTokenForDelivery(Constants.DELIVERY_AGENT_USERNAME);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        return loginResponseDto;
    }



}
