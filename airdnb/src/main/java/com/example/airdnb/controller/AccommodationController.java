package com.example.airdnb.controller;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.user.UserDetail;
import com.example.airdnb.dto.accommodation.AccommodationCreationRequest;
import com.example.airdnb.dto.accommodation.AccommodationResponse;
import com.example.airdnb.dto.review.ReviewCreateRequest;
import com.example.airdnb.dto.review.ReviewResponse;
import com.example.airdnb.service.AccommodationService;
import com.example.airdnb.service.ReviewService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodations")
@Slf4j
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final ReviewService reviewService;

    @GetMapping
    public List<AccommodationResponse> getAccommodationList() {
        return accommodationService.getAccommodationList();
    }

    @PostMapping
    public ResponseEntity<String> createNewAccommodation(@RequestBody AccommodationCreationRequest request) {
        log.info(request.toString());
        Accommodation createdAccommodation = accommodationService.createNewAccommodation(request);
        return ResponseEntity.created(URI.create("/accommodations/" + createdAccommodation.getId())).build();
    }

    @PostMapping("/{accommodationId}/reviews")
    public ResponseEntity<ReviewResponse> createNewReview(
        @AuthenticationPrincipal UserDetail userDetail,
        @PathVariable("accommodationId") Long accommodationId,
        @RequestBody @Valid ReviewCreateRequest request) {

        String username = userDetail.getUsername();
        ReviewResponse reviewResponse = reviewService.create(username, accommodationId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @GetMapping("/{accommodationId}/reviews")
    public List<ReviewResponse> getAllReviews(@PathVariable Long accommodationId) {
        return reviewService.getAllReviews(accommodationId);
    }

}
