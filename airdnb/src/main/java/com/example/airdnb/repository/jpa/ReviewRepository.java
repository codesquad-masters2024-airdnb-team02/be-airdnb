package com.example.airdnb.repository.jpa;

import com.example.airdnb.domain.review.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAccommodationId(Long accommodationId);
}
