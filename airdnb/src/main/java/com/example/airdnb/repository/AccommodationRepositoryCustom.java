package com.example.airdnb.repository;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import java.util.List;

public interface AccommodationRepositoryCustom {

    List<Accommodation> search(AccommodationSearchCond accommodationSearchCond);
}
