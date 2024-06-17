package com.example.airdnb.controller;

import com.example.airdnb.domain.accommodation.Accommodation;
import com.example.airdnb.domain.search.SearchCondition;
import com.example.airdnb.dto.search.SearchConditionRequest;
import com.example.airdnb.service.SearchService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public void search(@ModelAttribute @Valid SearchConditionRequest searchConditionRequest) {
        SearchCondition searchCondition = searchConditionRequest.toEntity();

        List<Accommodation> accommodations = searchService.searchWithConditions(searchCondition);

        log.info(searchCondition.toString());

        for (Accommodation accommodation : accommodations) {
            log.info(accommodation.toString());
        }
    }
}
