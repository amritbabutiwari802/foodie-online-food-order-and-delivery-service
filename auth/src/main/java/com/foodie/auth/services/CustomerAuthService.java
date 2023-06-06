package com.foodie.auth.services;

import com.foodie.auth.config.GrantedAuthority;
import com.foodie.auth.config.JwtUtils;
import com.foodie.auth.dao.CustomerAuthDao;
import com.foodie.auth.dto.CustomerAuthRegisterDto;
import com.foodie.auth.dto.AuthenticationResponse;
import com.foodie.auth.dto.LoginRequestDto;
import com.foodie.auth.dto.LoginResponseDto;
import com.foodie.auth.entity.CustomerAuth;
import com.foodie.auth.models.UserAuthority;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerAuthService {

    private final CustomerAuthDao customerAuthDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CustomerAuthService(CustomerAuthDao customerAuthDao, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.customerAuthDao = customerAuthDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    public String register(CustomerAuthRegisterDto customerAuthRegisterDto){
        String passwordHash = passwordEncoder.encode(customerAuthRegisterDto.getPassword());
        CustomerAuth customerAuth = new CustomerAuth();
        customerAuth.setCustomerId(customerAuthRegisterDto.getCustomerId());
        customerAuth.setEmail(customerAuthRegisterDto.getEmail());
        customerAuth.setPassword(passwordHash);
        this.customerAuthDao.save(customerAuth);
        return "created";
    }

    public LoginResponseDto login(LoginRequestDto customerLoginRequestDto){
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(customerLoginRequestDto.getEmail(),
                        customerLoginRequestDto.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
        }
        if(!authentication.getAuthorities().contains(GrantedAuthority.CUSTOMER)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
        }
        List<CustomerAuth> customerAuths = this.customerAuthDao.findByEmail(authentication.getPrincipal().toString());
        UUID customerid = customerAuths.get(0).getCustomerId();
        String jwtToken = this.jwtUtils.generateJwtTokenForCustomer(customerid.toString());
        LoginResponseDto customerLoginResponseDto = new LoginResponseDto();
        customerLoginResponseDto.setToken(jwtToken);
        return customerLoginResponseDto;
    }

    public AuthenticationResponse authenticateCustomer(String token){
        if(!this.jwtUtils.validateToken(token)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"worng token");
        }
        UUID principal = UUID.fromString(this.jwtUtils.getPrincipalFromJwtToken(token));
        UserAuthority userAuthority = this.jwtUtils.getAuthorityFromJwtToken(token);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setPrincipal(principal);
        authenticationResponse.setUserAuthority(userAuthority);
        return authenticationResponse;
    }

}
