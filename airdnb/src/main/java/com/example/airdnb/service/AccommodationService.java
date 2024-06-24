package com.example.airdnb.service;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.Role;
import com.example.airdnb.dto.accommodation.AccommodationCreationRequest;
import com.example.airdnb.dto.accommodation.search.AccommodationResponse;
import com.example.airdnb.repository.AccommodationRepository;
import com.example.airdnb.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public List<AccommodationResponse> searchWithCondition(AccommodationSearchCond searchCond) {
        List<Accommodation> accommodations = accommodationRepository.search(searchCond);
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
