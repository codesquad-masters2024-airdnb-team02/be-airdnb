package com.example.airdnb.repository;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.accommodation.QAccommodation;
import com.example.airdnb.domain.booking.QBooking;
import com.example.airdnb.domain.accommodation.search.PriceRange;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import com.example.airdnb.domain.accommodation.search.StayPeriod;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class AccommodationRepositoryCustomImpl implements AccommodationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccommodationRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Accommodation> search(AccommodationSearchCond accommodationSearchCond) {
        QAccommodation accommodation = QAccommodation.accommodation;
        QBooking booking = QBooking.booking;
        BooleanBuilder whereClause = new BooleanBuilder();

        addGuestCountCondition(whereClause, accommodationSearchCond.guestCount());
        addPriceRangeCondition(whereClause, accommodationSearchCond.priceRange());
        addStayPeriodCondition(whereClause, accommodationSearchCond.stayPeriod(), accommodation, booking);
        addLocationCondition(whereClause, accommodationSearchCond.centralLocation());

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

    private void addLocationCondition(BooleanBuilder whereClause, Point centralLocation) {
        if (centralLocation != null) {
            double radiusInMeters = 1000;
            double radiusInDegrees = radiusInMeters / 111000.0;

            double centralLat = centralLocation.getY();
            double centralLon = centralLocation.getX();

            GeometryFactory factory = new GeometryFactory();

            Point minPoint = factory.createPoint(new Coordinate(centralLon - radiusInDegrees, centralLat - radiusInDegrees));
            Point maxPoint = factory.createPoint(new Coordinate(centralLon + radiusInDegrees, centralLat + radiusInDegrees));

            whereClause.and(QAccommodation.accommodation.address.location.between(minPoint, maxPoint));
        }
    }
}
