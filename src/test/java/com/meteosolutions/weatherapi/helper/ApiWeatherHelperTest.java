package com.meteosolutions.weatherapi.helper;

import com.meteosolutions.weatherapi.dto.WeatherCityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
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


            when(webClient.get()).thenReturn(requestHeadersUriSpec);
            when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
            when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(locationKey));

            StepVerifier.create(apiWeatherHelper.getLocationKey(cityName))
                    .expectNext(locationKey)
                    .verifyComplete();
    }


        @Test
        void testGetWeatherInfo () {
            String locationKey = "12345";
            WeatherCityDTO weatherInfo = new WeatherCityDTO(); // Cambia WeatherInfoDTO por WeatherCityDTO o usa el DTO correcto que retornes

            when(webClient.get()).thenReturn(requestHeadersUriSpec);
            when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersSpec);
            when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(WeatherCityDTO.class)).thenReturn(Mono.just(weatherInfo)); // Cambia WeatherInfoDTO por WeatherCityDTO o usa el DTO correcto que retornes

            StepVerifier.create(apiWeatherHelper.getWeatherInfo(locationKey))
                    .expectNext(weatherInfo)
                    .verifyComplete();
    }

    }
