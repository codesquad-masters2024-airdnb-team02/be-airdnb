package com.example.airdnb.domain.accommodation;

import com.example.airdnb.domain.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long pricePerNight;

    @Column(nullable = false)
    private Integer maxGuests;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @Builder
    public Accommodation(Address address, User user, String name, String description,
        Long pricePerNight, Integer maxGuests, List<Image> images) {
        this.address = address;
        setUser(user);
        this.name = name;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        addImages(images);
    }

    private void addImages(List<Image> images) {
        if (images == null) {
            return;
        }
        images.forEach(this::addImage);
    }

    public void addImage(Image image) {
        image.setAccommodation(this);
        this.images.add(image);
    }

    public String getFullAddress() {
        return address.getFullAddress();
    }

    public void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
        user.addAccommodation(this);
    }

    public Image getRepresentativeImage() {
        // 임시 코드
        if (images.isEmpty()) {
            return null;
        }
        return images.get(0);
    }

    @Override
    public String toString() {
        return "Accommodation{" +
            "id=" + id +
            ", address=" + address +
            ", user=" + user.getName() +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", pricePerNight=" + pricePerNight +
            ", maxGuests=" + maxGuests +
            ", createdAt=" + createdAt +
            ", images=" + images +
            '}';
    }
}
