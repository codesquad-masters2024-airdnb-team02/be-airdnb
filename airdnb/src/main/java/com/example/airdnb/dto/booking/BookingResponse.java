package com.example.airdnb.dto.booking;

import com.example.airdnb.domain.booking.Booking;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BookingResponse(
    String guestName,
    String hostName,
    String accommodationName,
    String accommodationAddress,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal totalAmount,
    LocalDateTime createdAt
) {

    public static BookingResponse fromEntity(Booking booking) {
        return BookingResponse.builder()
            .guestName(booking.getUser().getName())
            .hostName(booking.getAccommodation().getUser().getName())
            .accommodationName(booking.getAccommodation().getName())
            .accommodationAddress(booking.getAccommodation().getFullAddress())
            .startDate(booking.getStartDate())
            .endDate(booking.getEndDate())
            .totalAmount(booking.getTotalAmount())
            .createdAt(booking.getCreatedAt())
            .build();

    }
}
