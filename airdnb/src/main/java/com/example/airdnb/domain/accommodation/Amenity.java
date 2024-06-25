package com.example.airdnb.domain.accommodation;

import java.util.Arrays;

public enum Amenity {
    SINGLE_BED, DOUBLE_BED, QUEEN_BED, AIR_CONDITIONER;

    private static final String INVALID_AMENITY_REQUEST_VALUE = "유효하지 않은 편의 시설 입력값 입니다.";

    public static Amenity from(String request) {
        String normalizedRequest = request.toUpperCase().replace(" ", "_");
        return Arrays.stream(values())
                .filter(amenity -> amenity.name().equals(normalizedRequest)) // toString 이 오버라이드 될 경우를 고려, name() 사용
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_AMENITY_REQUEST_VALUE));
    }
}
