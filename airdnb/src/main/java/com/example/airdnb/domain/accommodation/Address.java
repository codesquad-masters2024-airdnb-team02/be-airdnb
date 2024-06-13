package com.example.airdnb.domain.accommodation;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

@Embeddable
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String country;
    private String state;
    private String city;
    private String detail;
    private Point location;

    @Builder
    public Address(String country, String state, String city, String detail, Point location) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.detail = detail;
        this.location = location;
    }

    public String getFullAddress() {
        return country + " " + state + " " + city + " " + detail;
    }
}
