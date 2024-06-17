package com.example.airdnb.domain.search;

import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PriceRange {
    public static final long DEFAULT_MIN_VALUE = 0L;
    public static final long DEFAULT_MAX_VALUE = Long.MAX_VALUE;
    private final Long minPrice;
    private final Long maxPrice;

    private PriceRange(Long minPrice, Long maxPrice) {
        this.minPrice = Optional.ofNullable(minPrice).orElse(DEFAULT_MIN_VALUE);
        this.maxPrice = Optional.ofNullable(maxPrice).orElse(DEFAULT_MAX_VALUE);
    }

    public static PriceRange of(Long minPrice, Long maxPrice) {
        return new PriceRange(minPrice, maxPrice);
    }

    public static PriceRange defaultPriceRange() {
        return new PriceRange(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }
}
