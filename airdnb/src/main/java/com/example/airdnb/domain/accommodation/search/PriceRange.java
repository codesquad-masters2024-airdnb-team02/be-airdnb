package com.example.airdnb.domain.accommodation.search;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PriceRange {
    public static final BigDecimal DEFAULT_MIN_VALUE = BigDecimal.ZERO;
    public static final BigDecimal DEFAULT_MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;

    private PriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        this.minPrice = (minPrice != null) ? minPrice : DEFAULT_MIN_VALUE;
        this.maxPrice = (maxPrice != null) ? maxPrice : DEFAULT_MAX_VALUE;
    }

    public static PriceRange of(BigDecimal minPrice, BigDecimal maxPrice) {
        return new PriceRange(minPrice, maxPrice);
    }
}
