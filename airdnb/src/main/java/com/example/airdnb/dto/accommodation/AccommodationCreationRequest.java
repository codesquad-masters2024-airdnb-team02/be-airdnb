package com.example.airdnb.dto.accommodation;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.Address;
import com.example.airdnb.domain.accommodation.Amenity;
import com.example.airdnb.domain.accommodation.Image;
import com.example.airdnb.domain.user.User;
import java.util.List;
import java.util.Set;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public record AccommodationCreationRequest(
        AddressRequest address, // String country, String state, String city, String detail
        Long userId,
        String name,
        String description,
        Long pricePerNight,
        Integer maxGuests,
        Set<String> imageUrls,
        List<String > amenities) {

    public Accommodation toEntityWithHost(User host) {
        Address address = buildAddress();

        List<Image> images = getImagesFromRequest();

        List<Amenity> amenities = getAmenitiesFromRequest();

        return Accommodation.builder()
                .address(address)
                .user(host)
                .name(name)
                .pricePerNight(pricePerNight)
                .description(description)
                .maxGuests(maxGuests)
                .images(images)
                .amenities(amenities)
                .build();
    }

    private Address buildAddress() {
        Double latitude = this.address.latitude();
        Double longitude = this.address.longitude();
        Point point = getPointFromCoordinate(latitude, longitude);

        return new Address(
                this.address.country(),
                this.address.state(),
                this.address.city(),
                this.address.detail(),
                point
        );
    }

    private Point getPointFromCoordinate(Double latitude, Double longitude) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPoint(new Coordinate(latitude, longitude));
    }

    private List<Image> getImagesFromRequest() {
        return this.imageUrls().stream()
                .map(Image::new)
                .toList();
    }

    private List<Amenity> getAmenitiesFromRequest() {
        return this.amenities.stream()
                .map(Amenity::from)
                .toList();
    }
}