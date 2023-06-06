package com.foodie.resturantservice.repository;

import com.foodie.resturantservice.dao.*;
import com.foodie.resturantservice.dto.CreateRestaurantDto;
import com.foodie.resturantservice.entities.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestaurantRepository {

    private final AddressDao addressDao;
    private final BranchDao branchDao;
    private final RestaurantDao restaurantDao;
    private final BlockedRestaurantDao blockedRestaurantDao;

    public RestaurantRepository(AddressDao addressDao, BranchDao branchDao, RestaurantDao restaurantDao, BlockedRestaurantDao blockedRestaurantDao) {
        this.addressDao = addressDao;
        this.branchDao = branchDao;
        this.restaurantDao = restaurantDao;
        this.blockedRestaurantDao = blockedRestaurantDao;
    }

    public Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(createRestaurantDto.getName());
        restaurant.setRegistrationNumber(createRestaurantDto.getRegistrationNumber());
        restaurant.setPhoneNumber(createRestaurantDto.getPhoneNumber());
        restaurant.setEmail(createRestaurantDto.getEmail());
        Restaurant restaurantCreated = this.restaurantDao.save(restaurant);
        Branch branch = new Branch();
        branch.setRestaurantId(restaurantCreated.getId());
        branch.setPhoneNumber(createRestaurantDto.getPhoneNumber());
        branch.setEmail(createRestaurantDto.getEmail());
        Branch branchCreated = this.branchDao.save(branch);
        Address address = new Address();
        address.setRestaurant(restaurantCreated.getId());
        address.setBranchId(branchCreated.getId());
        address.setCountry(createRestaurantDto.getCountry());
        address.setProvince(createRestaurantDto.getProvince());
        address.setPostalCode(createRestaurantDto.getPostalCode());
        address.setCity(createRestaurantDto.getCity());
        address.setLocalAddress(createRestaurantDto.getLocalAddress());
        address.setAddressType(Address.AddressType.HEADQUARTER);
        this.addressDao.save(address);

        BlockedRestaurant blockedRestaurant = new BlockedRestaurant();
        blockedRestaurant.setRestaurantId(restaurantCreated.getId());
        blockedRestaurant.setBlocked(false);
        this.blockedRestaurantDao.save(blockedRestaurant);
        return restaurantCreated;
    }

    public void blockRestaurant(UUID restaurantId) {
        Optional<BlockedRestaurant> optional = this.blockedRestaurantDao.findById(restaurantId);
        BlockedRestaurant blockedRestaurant = optional.get();
        blockedRestaurant.setBlocked(true);
        this.blockedRestaurantDao.save(blockedRestaurant);
    }

    public void unBlockedRestaurant(UUID restaurantId){
        Optional<BlockedRestaurant> optional = this.blockedRestaurantDao.findById(restaurantId);
        BlockedRestaurant blockedRestaurant = optional.get();
        blockedRestaurant.setBlocked(false);
        this.blockedRestaurantDao.save(blockedRestaurant);
    }

    public boolean isRestaurantBlocked(UUID restaurantId){
        Optional<BlockedRestaurant> optional = this.blockedRestaurantDao.findById(restaurantId);
        BlockedRestaurant blockedRestaurant = optional.get();
        return blockedRestaurant.isBlocked();
    }

    public Address getAddress(UUID restaurantId, UUID branchId){
        List<Address> addressList = this.addressDao.findByRestaurantIdAndBranchId(restaurantId,branchId);
        return addressList.get(0);
    }

    public boolean isRestaurantIdValid(UUID restaurantId){
        Optional<Restaurant> optional = this.restaurantDao.findById(restaurantId);
        if(optional.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean isBranchIdValid(UUID branchId){
        Optional<Branch> branch = this.branchDao.findById(branchId);
        if(branch.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean isAlreadyRegistrated(String registrationNumber){
        List<Restaurant> restaurantList = this.restaurantDao.findByRegistrationNumber(registrationNumber);
        if(restaurantList.size()==0){
            return false;
        }
        return true;
    }
}
