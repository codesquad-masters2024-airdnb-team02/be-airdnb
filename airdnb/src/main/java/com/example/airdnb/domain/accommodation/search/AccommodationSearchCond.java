package com.example.airdnb.domain.accommodation.search;


import org.locationtech.jts.geom.Point;

/**
 * 검색 조건 요청
 *
 * @param stayPeriod NN / 클라이언트 요청 값이 반드시 존재 해야함
 * @param priceRange Nullable
 * @param guestCount Nullable
 */
public record AccommodationSearchCond(StayPeriod stayPeriod, PriceRange priceRange, Integer guestCount, Point centralLocation) {

    public AccommodationSearchCond {
        if (guestCount == null) {
            guestCount = 1;
        }
    }
}
