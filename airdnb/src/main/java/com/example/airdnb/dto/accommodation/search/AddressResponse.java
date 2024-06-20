package com.example.airdnb.dto.accommodation.search;

import com.example.airdnb.domain.accommodation.Address;
import lombok.Getter;

@Getter
public class AddressResponse {
    private final String country;
    private final String state;
    private final String city;
    private final String detail;
    private final Double latitude;
    private final Double longitude;

    private AddressResponse(Address address) {
        this.country = address.getCountry();
        this.state = address.getState();
        this.city = address.getCity();
        this.detail = address.getDetail();
        this.latitude = address.getLocation().getY();
        this.longitude = address.getLocation().getX();
    }

    public static AddressResponse from(Address address) {
        return new AddressResponse(address);
    }
}
