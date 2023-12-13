package com.meteosolutions.weatherapi.service;

import com.meteosolutions.weatherapi.entity.CityInfo;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.CityInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityInfoServicTest {
    @Mock
    private ApiWeatherHelper apiWeatherHelper;

    @Mock
    private CityInfoRepository cityInfoRepository;

    @InjectMocks
    private CityInfoService cityInfoService;

    @Test
    public void testtestSaveCityInfo(){
        String cityName = "Dummy City";
        String locationKey = "LocationKey";

        when(apiWeatherHelper.getLocationKey(cityName))
                .thenReturn(Mono.just(locationKey));

        CityInfo cityInfo = new CityInfo();
        cityInfo.setKey(locationKey);
        cityInfo.setLocalizedName(cityName);

        when(cityInfoRepository.save(any())).thenReturn(cityInfo);

        CityInfo savedCityInfo = cityInfoService.saveCityInfo(cityName);

        assertNotNull(savedCityInfo);
        assertEquals(cityInfo.getKey(), savedCityInfo.getKey());
        assertEquals(cityInfo.getLocalizedName(), savedCityInfo.getLocalizedName());

    }

}