package com.example.airdnb.dto.accommodation.search;

import com.example.airdnb.domain.accommodation.search.PriceRange;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import com.example.airdnb.domain.accommodation.search.StayPeriod;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.ToString;

@ToString
public class AccommodationSearchCondRequest {

    @NotNull
    private final StayPeriod stayPeriod;
    private final PriceRange priceRange;
    private final Integer guestCount;

    public AccommodationSearchCondRequest(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Long minPrice,
            Long maxPrice,
            Integer guestCount) {
        this.stayPeriod = StayPeriod.of(checkInDate, checkOutDate);
        this.priceRange = PriceRange.of(minPrice, maxPrice);
        this.guestCount = guestCount;
    }

    public AccommodationSearchCond toEntity() {
        return new AccommodationSearchCond(
                this.stayPeriod,
                this.priceRange,
                this.guestCount);
    }
}
