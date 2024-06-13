package com.example.airdnb.dto.image;

import com.example.airdnb.domain.accommodation.Image;

public record ImageResponse(String url) {

    public static ImageResponse from(Image image) {
        return new ImageResponse(image.getUrl());
    }
}
