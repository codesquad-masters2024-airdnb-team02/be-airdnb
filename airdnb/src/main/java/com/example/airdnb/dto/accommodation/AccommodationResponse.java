package com.example.airdnb.dto.accommodation;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.Address;
import com.example.airdnb.domain.accommodation.Image;
import com.example.airdnb.dto.image.ImageResponse;

public record AccommodationResponse(
        Long id,
        Address address,
        String name,
        String description,
        Long pricePerNight,
        Integer maxGuests,
        ImageResponse image) {

    public static AccommodationResponse of(Accommodation accommodation) {

        Image representativeImage = accommodation.getRepresentativeImage();

        return new AccommodationResponse(
                accommodation.getId(),
                accommodation.getAddress(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests(),
                ImageResponse.from(representativeImage));
    }
}
