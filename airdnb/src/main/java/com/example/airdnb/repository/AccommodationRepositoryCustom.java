package com.example.airdnb.repository;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.search.SearchCondition;
import java.util.List;

public interface AccommodationRepositoryCustom {

    List<Accommodation> search(SearchCondition searchCondition);
}
