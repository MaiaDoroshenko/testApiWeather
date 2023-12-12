package com.meteosolutions.weatherapi.helper;

import com.meteosolutions.weatherapi.dto.WeatherInfoDTO;
import com.meteosolutions.weatherapi.exception.CustomClientException;
import com.meteosolutions.weatherapi.exception.CustomServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiWeatherHelperTest {
    private String locationQueryParam="New York";
    private String baseApiUrl="http://dataservice.accuweather.com/locations/v1";
    private String locationsPath="/cities/search?apikey=16GOwQqxqvo13eabRAcTw8WzmqR0pdHX&q=New%20York";
    private String forecastsPath="/daily/1day/{locationKey}?apikey=${api.apiKey}";
    private String apiKey="16GOwQqxqvo13eabRAcTw8WzmqR0pdHX";



        private WebClient.Builder webClientBuilder=WebClient.builder();

        @Mock
        private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

        @Mock
        private WebClient.RequestHeadersSpec requestHeadersSpec;

        @Mock
        private WebClient.ResponseSpec responseSpec;

        @Mock
        private WebClient webClient;
        private ApiWeatherHelper apiWeatherHelper;


        @BeforeEach
        public void setUp () {
        MockitoAnnotations.openMocks(this);
            apiWeatherHelper = new ApiWeatherHelper(webClientBuilder,
                    locationQueryParam,
                    baseApiUrl,
                    locationsPath,
                    forecastsPath,
                    apiKey);
    }

        @Test
        void testGetLocationKey_Success () {
        apiWeatherHelper = new ApiWeatherHelper(webClientBuilder,
                locationQueryParam,
                baseApiUrl,
                locationsPath,
                forecastsPath,
                apiKey);

        String cityName = "New York";
        String locationKey = "12345";


        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(locationKey));

        StepVerifier.create(apiWeatherHelper.getLocationKey(cityName))
                .expectNext(locationKey)
                .verifyComplete();
    }


        @Test
        void testGetWeatherInfo () {
        String locationKey = "12345";
        WeatherInfoDTO weatherInfo = new WeatherInfoDTO();

        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(WeatherInfoDTO.class)).thenReturn(Mono.just(weatherInfo));

        StepVerifier.create(apiWeatherHelper.getWeatherInfo(locationKey))
                .expectNext(weatherInfo)
                .verifyComplete();
    }

    }
