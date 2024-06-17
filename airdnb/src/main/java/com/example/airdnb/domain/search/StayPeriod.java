package com.example.airdnb.domain.search;

import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class StayPeriod {

    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    private StayPeriod(LocalDate checkInDate, LocalDate checkOutDate) {
        validateDates(checkInDate, checkOutDate);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    private void validateDates(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate.isAfter(checkOutDate) || checkInDate.isEqual(checkOutDate)) {
            throw new RuntimeException(); // 예외 변경 예정
        }
    }

    public static StayPeriod of(LocalDate checkInDate, LocalDate checkOutDate) {
        return new StayPeriod(checkInDate, checkOutDate);
    }
}
