package com.example.airdnb.dto.search;

import com.example.airdnb.domain.search.PriceRange;
import com.example.airdnb.domain.search.SearchCondition;
import com.example.airdnb.domain.search.StayPeriod;
import lombok.ToString;

@ToString
public class SearchConditionRequest {
    private final StayPeriod stayPeriod;
    private final PriceRange priceRange;
    private final Integer guestCount;

    public SearchConditionRequest(StayPeriod stayPeriod, PriceRange priceRange, Integer guestCount) {
        this.stayPeriod = stayPeriod;
        this.priceRange = priceRange;
        this.guestCount = guestCount;
    }

    public SearchCondition toEntity() {
        return new SearchCondition(
                this.stayPeriod,
                this.priceRange,
                this.guestCount);
    }
}
