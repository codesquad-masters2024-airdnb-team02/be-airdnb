package com.example.airdnb.repository;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.QAccommodation;
import com.example.airdnb.domain.search.PriceRange;
import com.example.airdnb.domain.search.SearchCondition;
import com.example.airdnb.domain.search.StayPeriod;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AccommodationRepositoryCustomImpl implements AccommodationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccommodationRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Accommodation> search(SearchCondition searchCondition) {

        QAccommodation accommodation = QAccommodation.accommodation;
        BooleanBuilder whereClause = new BooleanBuilder();

        if (searchCondition.priceRange() != null) {
            PriceRange priceRange = searchCondition.priceRange();
            if (priceRange.getMinPrice() != null) {
                whereClause.and(accommodation.pricePerNight.goe(priceRange.getMinPrice()));
            }
            if (priceRange.getMaxPrice() != null) {
                whereClause.and(accommodation.pricePerNight.loe(priceRange.getMaxPrice()));
            }
        }

        if (searchCondition.guestCount() != null) {
            whereClause.and(accommodation.maxGuests.goe(searchCondition.guestCount()));
        }

        StayPeriod stayPeriod = searchCondition.stayPeriod();

        BooleanBuilder bookingClause = new BooleanBuilder();
        bookingClause.or(accommodation.bookings.isEmpty())
                .or(accommodation.bookings.any().startDate.loe(stayPeriod.getCheckInDate())
                        .and(accommodation.bookings.any().endDate.goe(stayPeriod.getCheckOutDate())));
        whereClause.and(bookingClause);

        return queryFactory.selectFrom(accommodation)
                .where(whereClause)
                .fetch();
    }
}
