package com.example.airdnb.dto.accommodation.search;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.dto.accommodation.AddressResponse;

/**
 *
 각 숙소 세부 정보
 숙소 이름
 숙소 주소 (전체 주소와 위도 경도 : 프론트에서 지도 화면 관련 로직 처리)
 숙소 대표 이미지
 평점 (5만점)
 후기 갯수
 1박당 가격
 편의 시설 (다중) - [추후 적용 예정]
 */
public record AccommodationResponse(
        Long id,
        String name,
        AddressResponse address,
        String representativeImageUrl,
        ReviewSummaryResponse review,
        Long pricePerNight,
        Integer maxGuests) {

    public static AccommodationResponse of(Accommodation accommodation, ReviewSummaryResponse review) {

        String imageUrl = accommodation.getRepresentativeImage().getUrl();
        AddressResponse addressResponse = AddressResponse.from(accommodation.getAddress());

        return new AccommodationResponse(
                accommodation.getId(),
                accommodation.getName(),
                addressResponse,
                imageUrl,
                review,
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests()
        );
    }
}
