package com.example.airdnb.service;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.dto.accommodation.AccommodationResponse;
import com.example.airdnb.repository.accommodation.AccommodationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public List<AccommodationResponse> getAccommodationList() { // 검색조건에 따른 결과를 가져오도록 변경 해야함

        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream()
                .map(AccommodationResponse::of)
                .toList();
    }
}
