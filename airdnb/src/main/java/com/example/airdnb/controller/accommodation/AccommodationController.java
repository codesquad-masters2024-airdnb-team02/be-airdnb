package com.example.airdnb.controller.accommodation;

import com.example.airdnb.dto.accommodation.AccommodationResponse;
import com.example.airdnb.service.AccommodationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    public List<AccommodationResponse> getAccommodationList() {
        return accommodationService.getAccommodationList();
    }
}
