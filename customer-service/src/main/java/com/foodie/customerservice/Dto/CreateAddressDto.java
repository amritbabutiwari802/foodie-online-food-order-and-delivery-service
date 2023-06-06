package com.foodie.customerservice.Dto;

import com.foodie.customerservice.entities.Address;

import java.util.UUID;

public class CreateAddressDto {
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String localAddress;


    // Default constructor
    public CreateAddressDto() {
    }

    // Parameterized constructor
    public CreateAddressDto( String country, String province, String city, String postalCode, String localAddress) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
        this.localAddress = localAddress;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }



    @Override
    public String toString() {
        return "CreateAddressDto{" +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", localAddress='" + localAddress + '\'' +
                '}';
    }
}
