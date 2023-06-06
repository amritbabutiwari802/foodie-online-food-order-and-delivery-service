package com.foodie.auth.config.springsecurity;

import com.foodie.auth.config.Constants;
import com.foodie.auth.config.GrantedAuthority;
import com.foodie.auth.dao.CustomerAuthDao;
import com.foodie.auth.dao.RestaurantAuthDao;
import com.foodie.auth.entity.CustomerAuth;
import com.foodie.auth.entity.RestaurantAuth;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private final RestaurantAuthDao restaurantAuthDao;
    private final CustomerAuthDao customerAuthDao;

    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(RestaurantAuthDao restaurantAuthDao, CustomerAuthDao customerAuthDao, PasswordEncoder passwordEncoder) {
        this.restaurantAuthDao = restaurantAuthDao;
        this.customerAuthDao = customerAuthDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (username) {
            // case Delivery agent
            case Constants.DELIVERY_AGENT_USERNAME:
                authorities.add(GrantedAuthority.DELIVERY);
                String password = this.passwordEncoder.encode(Constants.DELIVERY_AGENT_PASSWORD);
                userDetails = new User(username, password, authorities);
                break;
            default:
                //case restaurant
                if (username.endsWith(Constants.RESTURANT_USERNAME_SUFFIX)) {
                    List<RestaurantAuth> restaurantAuthList = this.restaurantAuthDao.findByEmail(username);
                    if(restaurantAuthList.size()==0){
                        throw new UsernameNotFoundException("invalid data");
                    }
                    RestaurantAuth restaurantAuth = restaurantAuthList.get(0);
                    authorities.add(GrantedAuthority.RESTAURANT);
                    userDetails = new User(username,restaurantAuth.getPassword(), authorities);
                }
                //case customer
                else {
                    List<CustomerAuth> customerAuths = this.customerAuthDao.findByEmail(username);
                    if(customerAuths.size()==0){
                        throw new UsernameNotFoundException("invalid data");
                    }
                    CustomerAuth customerAuth = customerAuths.get(0);
                    authorities.add(GrantedAuthority.CUSTOMER);
                    userDetails = new User(username, customerAuth.getPassword(), authorities);
                }

        }
        return userDetails;
    }
}
