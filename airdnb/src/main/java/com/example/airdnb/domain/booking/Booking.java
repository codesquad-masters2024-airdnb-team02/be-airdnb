package com.example.airdnb.domain.booking;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Booking(User user, Accommodation accommodation, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = calculateAmount(accommodation.getPricePerNight(), startDate, endDate);
    }

    private BigDecimal calculateAmount(Long pricePerNight, LocalDate startDate, LocalDate endDate) {
        long between = ChronoUnit.DAYS.between(startDate, endDate);
        return totalAmount = BigDecimal.valueOf(pricePerNight * between);
    }

}
