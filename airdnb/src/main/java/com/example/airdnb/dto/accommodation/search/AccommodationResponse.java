package com.example.airdnb.dto.accommodation.search;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.dto.accommodation.AddressResponse;

/**
 *
 각 숙소 세부 정보
 숙소 이름
 숙소 주소 (어떤 형식? ex) )
 숙소 대표 이미지
 평점 (5만점) - [추후 적용 예정]
 후기 갯수 - [추후 적용 예정]
 1박당 가격
 편의 시설 (다중) - [추후 적용 예정]
 */
public record AccommodationResponse(
        Long id,
        String name,
        AddressResponse address,
        String representativeImageUrl,
        Long pricePerNight,
        Integer maxGuests) {

    public static AccommodationResponse of(Accommodation accommodation) {

        String imageUrl = accommodation.getRepresentativeImage().getUrl();
        AddressResponse addressResponse = AddressResponse.from(accommodation.getAddress());

        return new AccommodationResponse(
                accommodation.getId(),
                accommodation.getName(),
                addressResponse,
                imageUrl,
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests()
        );
    }
}
