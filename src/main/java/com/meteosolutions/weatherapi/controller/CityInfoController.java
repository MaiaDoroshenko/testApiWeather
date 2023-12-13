package com.meteosolutions.weatherapi.controller;

import com.meteosolutions.weatherapi.entity.CityInfo;
import com.meteosolutions.weatherapi.service.CityInfoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
@AllArgsConstructor
public class CityInfoController {
    private final CityInfoService cityInfoService;

    @PostMapping("/add")
    public ResponseEntity<String> addCityInfo(@RequestBody String cityName) {
        CityInfo cityInfo = cityInfoService.saveCityInfo(cityName);
        return new ResponseEntity<>("City added with ID: " + cityInfo.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/get/{cityName}")
    public ResponseEntity<CityInfo> getCityInfo(@PathVariable String cityName) {
        CityInfo cityInfo = cityInfoService.getCityInfoByName(cityName);
        if (cityInfo != null) {
            return new ResponseEntity<>(cityInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
