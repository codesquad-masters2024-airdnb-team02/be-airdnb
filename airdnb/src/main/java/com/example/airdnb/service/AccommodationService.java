package com.example.airdnb.service;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.Role;
import com.example.airdnb.dto.accommodation.AccommodationCreationRequest;
import com.example.airdnb.dto.accommodation.AccommodationResponse;
import com.example.airdnb.repository.jpa.AccommodationRepository;
import com.example.airdnb.repository.jpa.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public List<AccommodationResponse> getAccommodationList() { // 검색 조건에 따른 결과를 가져오도록 변경 해야함

        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream()
                .map(AccommodationResponse::of)
                .toList();
    }

    public Accommodation createNewAccommodation(AccommodationCreationRequest request) {

        // 호스트에 해당하는 사용자를 임시로 생성하는 코드
        // 추후 변경 예정
        User saveUser = userRepository.save(new User("abc", "bde", "a", Role.HOST));
        User host = findUserById(saveUser.getId());

        Accommodation accommodation = request.toEntityWithHost(host);

        return accommodationRepository.save(accommodation);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
