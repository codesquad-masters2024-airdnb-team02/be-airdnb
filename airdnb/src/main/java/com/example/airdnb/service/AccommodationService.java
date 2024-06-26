package com.example.airdnb.service;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import com.example.airdnb.domain.review.Review;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.dto.accommodation.AccommodationCreationRequest;
import com.example.airdnb.dto.accommodation.search.AccommodationResponse;
import com.example.airdnb.dto.accommodation.search.AccommodationSearchCondRequest;
import com.example.airdnb.dto.accommodation.search.ReviewSummaryResponse;
import com.example.airdnb.exception.AccommodationNotFoundException;
import com.example.airdnb.exception.UserNotFoundException;
import com.example.airdnb.repository.AccommodationRepository;
import com.example.airdnb.repository.ReviewRepository;
import com.example.airdnb.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public List<AccommodationResponse> searchWithCondition(AccommodationSearchCondRequest searchCondRequest) {
        AccommodationSearchCond searchCond = searchCondRequest.toEntity();
        List<Accommodation> accommodations = accommodationRepository.search(searchCond);

        return accommodations.stream()
                .map(accommodation -> {
                    ReviewSummaryResponse reviewSummary = createReviewSummaryOfAccommodation(accommodation.getId());
                    return AccommodationResponse.of(accommodation, reviewSummary);
                })
                .toList();
    }

    public Accommodation createNewAccommodation(AccommodationCreationRequest request) {

        // 호스트에 해당하는 사용자를 임시로 생성하는 코드
        // 추후 변경 예정
//        UUID uuid = UUID.randomUUID();
//        User saveUser = userRepository.save(new User("abc" + uuid, "bde", "a", Role.HOST));
        User host = findUserById(1L);

        Accommodation accommodation = request.toEntityWithHost(host);

        return accommodationRepository.save(accommodation);
    }

    @PreAuthorize("hasRole('ADMIN') or @accommodationService.isHost(#authentication.name, #accommodationId)")
    public void deleteAccommodation(Long accommodationId) {
        Accommodation accommodation = findAccommodationById(accommodationId);

        accommodationRepository.delete(accommodation);
    }

    public boolean isHost(String userEmail, Long accommodationId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(IllegalArgumentException::new);

        Accommodation accommodation = findAccommodationById(accommodationId);

        Long hostId = accommodation.getUser().getId();

        return hostId.equals(user.getId());
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private Accommodation findAccommodationById(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
    }

    private ReviewSummaryResponse createReviewSummaryOfAccommodation(Long accommodationId) {
        List<Review> reviews = reviewRepository.findAllByAccommodationId(accommodationId);

        double averageRating = calculateAverageRating(reviews);

        int totalReviewCount = reviews.size();

        return new ReviewSummaryResponse(totalReviewCount, averageRating);
    }

    private double calculateAverageRating(List<Review> reviews) {
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
