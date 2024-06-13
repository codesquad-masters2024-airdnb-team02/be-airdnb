package com.example.airdnb.dto.booking;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.booking.Booking;
import com.example.airdnb.domain.user.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookingCreateRequest(
    @NotNull Long userId,
    @NotNull Long accommodationId,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate
) {

    public Booking toEntity(User user, Accommodation accommodation) {
        return Booking.builder()
            .user(user)
            .accommodation(accommodation)
            .startDate(startDate())
            .endDate(endDate())
            .build();
    }

}
