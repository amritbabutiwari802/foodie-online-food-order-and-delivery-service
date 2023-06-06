package com.foodie.customerservice.entities;

import javax.persistence.*;
import java.util.UUID;


@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID customerId;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String localAddress;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    // Default constructor
    public Address() {
        this.id = UUID.randomUUID();
    }

    // Parameterized constructor
    public Address(UUID customerId, String country, String province, String city, String postalCode, String localAddress, AddressType addressType) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.country = country;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
        this.localAddress = localAddress;
        this.addressType = addressType;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
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

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    // AddressType enum definition
    public enum AddressType {
        DEFAULT_ADDRESS,
        OTHER_ADDRESS
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", localAddress='" + localAddress + '\'' +
                ", addressType=" + addressType +
                '}';
    }
}
