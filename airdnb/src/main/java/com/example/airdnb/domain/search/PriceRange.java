package com.example.airdnb.domain.search;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PriceRange {
    private final Long minPrice;
    private final Long maxPrice;

    private PriceRange(Long minPrice, Long maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static PriceRange of(Long minPrice, Long maxPrice) {
        return new PriceRange(minPrice, maxPrice);
    }
}
