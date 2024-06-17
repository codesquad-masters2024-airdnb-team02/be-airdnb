package com.example.airdnb.repository;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.QAccommodation;
import com.example.airdnb.domain.booking.QBooking;
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
        QBooking booking = QBooking.booking;
        BooleanBuilder whereClause = new BooleanBuilder();

        addGuestCountCondition(whereClause, searchCondition.guestCount());
        addPriceRangeCondition(whereClause, searchCondition.priceRange());
        addStayPeriodCondition(whereClause, searchCondition.stayPeriod(), accommodation, booking);

        return queryFactory.selectFrom(accommodation)
                .where(whereClause)
                .fetch();
    }

    private void addGuestCountCondition(BooleanBuilder whereClause, Integer guestCount) {
        whereClause.and(QAccommodation.accommodation.maxGuests.goe(guestCount));
    }

    private void addPriceRangeCondition(BooleanBuilder whereClause, PriceRange priceRange) {
        whereClause.and(QAccommodation.accommodation.pricePerNight.goe(priceRange.getMinPrice()))
                .and(QAccommodation.accommodation.pricePerNight.loe(priceRange.getMaxPrice()));
    }

    private void addStayPeriodCondition(BooleanBuilder whereClause, StayPeriod stayPeriod, QAccommodation accommodation, QBooking booking) {
        BooleanBuilder bookingClause = new BooleanBuilder();
        bookingClause.or(
                queryFactory.selectFrom(booking)
                        .where(booking.accommodation.eq(accommodation)
                                .and(booking.startDate.lt(stayPeriod.getCheckOutDate()))
                                .and(booking.endDate.gt(stayPeriod.getCheckInDate()))
                        ).notExists()
        );
        whereClause.and(bookingClause);
    }
}
