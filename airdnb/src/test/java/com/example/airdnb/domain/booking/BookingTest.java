package com.example.airdnb.domain.booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    @DisplayName("체크아웃 날짜와 체크인 날짜로 숙박일을 구할 수 있다.")
    void calculateBetweenDate() {

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 3);
        long between = ChronoUnit.DAYS.between(startDate, endDate);

        Assertions.assertThat(between).isEqualTo(2);
    }

}