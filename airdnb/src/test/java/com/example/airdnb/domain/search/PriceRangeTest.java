package com.example.airdnb.domain.search;

import static org.assertj.core.api.Assertions.*;

import com.example.airdnb.dto.search.SearchConditionRequest;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceRangeTest {

    @Test
    @DisplayName("최소값이 null 일 경우 지정된 기본값으로 객체가 생성되어야 한다")
    void minDefaultValueTest() {
        PriceRange priceRange = PriceRange.of(null, 100L);

        assertThat(priceRange.getMinPrice()).isEqualTo(PriceRange.DEFAULT_MIN_VALUE);
    }

    @Test
    @DisplayName("최대값이 null 일 경우 지정된 기본값으로 객체가 생성되어야 한다")
    void maxDefaultValueTest() {
        PriceRange priceRange = PriceRange.of(2L, null);

        assertThat(priceRange.getMaxPrice()).isEqualTo(PriceRange.DEFAULT_MAX_VALUE);
    }

    @Test
    @DisplayName("검색조건에서 PriceRange가 null 일 경우 지정된 기본값으로 객체가 생성되어야 한다\"")
    void priceRangeDefaultValueTest() {
        SearchConditionRequest searchConditionRequest = new SearchConditionRequest(
                StayPeriod.of(LocalDate.now(), LocalDate.now().plusDays(2)),
                null,
                2
        );

        SearchCondition searchCondition = searchConditionRequest.toEntity();
        PriceRange priceRange = searchCondition.priceRange();

        assertThat(priceRange.getMinPrice()).isEqualTo(PriceRange.DEFAULT_MIN_VALUE);
        assertThat(priceRange.getMaxPrice()).isEqualTo(PriceRange.DEFAULT_MAX_VALUE);
    }
}
