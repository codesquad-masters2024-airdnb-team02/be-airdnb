package com.example.airdnb.repository.jpa;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.repository.AccommodationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, AccommodationRepositoryCustom {

}
