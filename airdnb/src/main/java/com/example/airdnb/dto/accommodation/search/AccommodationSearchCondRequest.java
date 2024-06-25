package com.example.airdnb.dto.accommodation.search;

import com.example.airdnb.domain.accommodation.search.PriceRange;
import com.example.airdnb.domain.accommodation.search.AccommodationSearchCond;
import com.example.airdnb.domain.accommodation.search.StayPeriod;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.ToString;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@ToString
public class AccommodationSearchCondRequest {

    @NotNull
    private final StayPeriod stayPeriod;
    private final PriceRange priceRange;
    private final Integer guestCount;
    private final Double latitude;
    private final Double longitude;

    public AccommodationSearchCondRequest(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer guestCount,
            Double latitude,
            Double longitude) {
        this.stayPeriod = StayPeriod.of(checkInDate, checkOutDate);
        this.priceRange = PriceRange.of(minPrice, maxPrice);
        this.guestCount = guestCount;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AccommodationSearchCond toEntity() {
        return new AccommodationSearchCond(
                this.stayPeriod,
                this.priceRange,
                this.guestCount,
                getPointFromCoordinate(this.latitude, this.longitude)
                );
    }

    private Point getPointFromCoordinate(Double latitude, Double longitude) {
        if (latitude == null) {
            latitude = 37.49078586123461;
        }
        if (longitude == null) {
            longitude = 127.03345345373177;
        }
        GeometryFactory gf = new GeometryFactory();
        return gf.createPoint(new Coordinate(latitude, longitude));
    }
}
