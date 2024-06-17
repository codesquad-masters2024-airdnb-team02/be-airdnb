package com.example.airdnb.dto.search;

import com.example.airdnb.domain.search.PriceRange;
import com.example.airdnb.domain.search.SearchCondition;
import com.example.airdnb.domain.search.StayPeriod;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.ToString;

@ToString
public class SearchConditionRequest {

    @NotNull
    private final StayPeriod stayPeriod;
    private final PriceRange priceRange;
    private final Integer guestCount;

    public SearchConditionRequest(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Long minPrice,
            Long maxPrice,
            Integer guestCount) {
        this.stayPeriod = StayPeriod.of(checkInDate, checkOutDate);
        this.priceRange = PriceRange.of(minPrice, maxPrice);
        this.guestCount = guestCount;
    }

    public SearchCondition toEntity() {
        return new SearchCondition(
                this.stayPeriod,
                this.priceRange,
                this.guestCount);
    }
}
