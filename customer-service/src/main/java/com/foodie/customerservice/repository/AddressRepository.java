package com.foodie.customerservice.repository;

import com.foodie.customerservice.Dto.CreateAddressDto;
import com.foodie.customerservice.dao.AddressDao;
import com.foodie.customerservice.entities.Address;
import com.foodie.customerservice.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AddressRepository {
    private final AddressDao addressDao;

    public AddressRepository(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address addAddress(UUID customerId, CreateAddressDto createAddressDto){
        Address address = new Address();
        address.setCustomerId(customerId);
        address.setCountry( createAddressDto.getCountry());
        address.setProvince(createAddressDto.getProvince());
        address.setCity(createAddressDto.getCity());
        address.setPostalCode(createAddressDto.getPostalCode());
        address.setLocalAddress(createAddressDto.getLocalAddress());
        address.setAddressType(Address.AddressType.OTHER_ADDRESS);
        return this.addressDao.save(address);
    }

    public Address updateAddress(UUID addressId, CreateAddressDto createAddressDto){
        Optional<Address> optional = this.addressDao.findById(addressId);
        if(optional.isEmpty()){
            return null;
        }
        Address address = optional.get();
        address.setCountry( createAddressDto.getCountry());
        address.setProvince(createAddressDto.getProvince());
        address.setCity(createAddressDto.getCity());
        address.setPostalCode(createAddressDto.getPostalCode());
        address.setLocalAddress(createAddressDto.getLocalAddress());
        address.setAddressType(Address.AddressType.OTHER_ADDRESS);
        return this.addressDao.save(address);
    }

    public Address channgeDefaultAddress(UUID customerId, UUID addressId){
        List<Address> addressList = this.addressDao.findByCustomerIdAndAddressType(customerId, Address.AddressType.DEFAULT_ADDRESS);
        Optional<Address> optional = this.addressDao.findById(addressId);
        if(optional.isEmpty()){
            return null;
        }
        Address defaultAddress = optional.get();
        Address temp = addressList.get(0);
        if(defaultAddress.getId().equals(temp.getId())){
            return defaultAddress;
        }
        temp.setAddressType(Address.AddressType.OTHER_ADDRESS);
        defaultAddress.setAddressType(Address.AddressType.DEFAULT_ADDRESS);
        this.addressDao.save(temp);
        return this.addressDao.save(defaultAddress);
    }

    public boolean deleteAddress(UUID addressId){
        Optional<Address> optional = this.addressDao.findById(addressId);
        if(optional.isEmpty()){
            return false;
        }
        Address address = optional.get();
        if(address.getAddressType()== Address.AddressType.DEFAULT_ADDRESS){
            return  false;
        }
        this.addressDao.delete(address);
        return true;
    }

    public boolean matchCustomerIdAndAddressId(UUID customerId, UUID addressId){
        Optional<Address> optional = this.addressDao.findById(addressId);
        Address address = optional.get();
        if(address.getCustomerId().equals(customerId)){
            return true;
        }
        return false;
    }

}
