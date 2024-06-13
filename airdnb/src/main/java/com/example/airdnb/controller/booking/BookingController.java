package com.example.airdnb.controller.booking;

import com.example.airdnb.domain.booking.Booking;
import com.example.airdnb.dto.booking.BookingCreateRequest;
import com.example.airdnb.dto.booking.BookingResponse;
import com.example.airdnb.service.booking.BookingService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody @Valid BookingCreateRequest request) {
        Long bookingId = bookingService.create(request);
        URI uri = URI.create(String.format("/bookings/%d", bookingId));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public BookingResponse get(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

}
