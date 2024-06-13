package com.example.airdnb.service.booking;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.booking.Booking;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.dto.booking.BookingCreateRequest;
import com.example.airdnb.dto.booking.BookingResponse;
import com.example.airdnb.repository.accommodation.AccommodationRepository;
import com.example.airdnb.repository.booking.BookingRepository;
import com.example.airdnb.repository.user.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    public Long create(BookingCreateRequest request) {
        User user = userRepository.findById(request.userId())
            .orElseThrow(NoSuchElementException::new);
        Accommodation accommodation = accommodationRepository.findById(request.accommodationId())
            .orElseThrow(NoSuchElementException::new);

        Booking booking = request.toEntity(user, accommodation);
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking.getId();
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return BookingResponse.fromEntity(booking);
    }
}
