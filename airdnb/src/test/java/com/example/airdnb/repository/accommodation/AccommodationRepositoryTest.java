package com.example.airdnb.repository.accommodation;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.Address;
import com.example.airdnb.domain.accommodation.Image;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.domain.user.User.Role;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void save() {
        User user = User.builder()
            .email("cori@naver.com")
            .role(Role.HOST)
            .password("1234")
            .name("cori")
            .build();

        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint(new Coordinate(122, 37));

        Image image = new Image("url");

        Accommodation accommodation = Accommodation.builder()
            .address(new Address("대한민국", "서울시", "역삼동", "뱅뱅사거리로", point))
            .user(user)
            .name("코드스쿼드")
            .description("강남 최고의 숙소")
            .images(List.of(image))
            .maxGuests(3)
            .pricePerNight(10000L)
            .build();

        Accommodation savedAccommodation = accommodationRepository.save(accommodation);

        assertThat(savedAccommodation).usingRecursiveComparison().isEqualTo(accommodation);
    }
}