package com.tomdoischer.booze_scraping.controller;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.dto.WhiskyBottleDto;
import com.tomdoischer.booze_scraping.service.WhiskyBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whisky-bottle")
public class WhiskyBottleController {

    private final WhiskyBottleService whiskyBottleService;

    @Autowired
    public WhiskyBottleController(WhiskyBottleService whiskyBottleService) {
        this.whiskyBottleService = whiskyBottleService;
    }

    @GetMapping("/all")
    public List<WhiskyBottleDto> getAllWhiskyBottles() {
        return whiskyBottleService.findAllDto();
    }


}
