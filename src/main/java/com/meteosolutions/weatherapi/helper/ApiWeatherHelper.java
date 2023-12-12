package com.meteosolutions.weatherapi.helper;

import com.meteosolutions.weatherapi.dto.WeatherInfoDTO;
import com.meteosolutions.weatherapi.exception.CustomClientException;
import com.meteosolutions.weatherapi.exception.CustomServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ApiWeatherHelper {
    private WebClient webClient;
    private String locationQueryParam;

    private String baseApiUrl;
    private String locationsPath;
    private String forecastsPath;

    private String apiKey;


    public ApiWeatherHelper(WebClient.Builder webClientBuilder,
                            @Value("${user.base.name}") String locationQueryParam,
                            @Value("${api.base.url}") String baseApiUrl,
                            @Value("${api.locations.url}") String locationsPath,
                            @Value("${api.forecasts.url}") String forecastsPath,
                            @Value("${api.apiKey}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(baseApiUrl).build();
        this.locationQueryParam = locationQueryParam;
        this.baseApiUrl = baseApiUrl;
        this.locationsPath = locationsPath;
        this.forecastsPath = forecastsPath;
        this.apiKey = apiKey;


        log.info("Base API URL: {}", baseApiUrl);
        log.info("Locations Path: {}", locationsPath);
        log.info("Forecasts Path: {}", forecastsPath);
        log.info("API Key apiKey : {} ;", apiKey);
        log.info("Location Query Param : {};", locationQueryParam);

    }



    private String buildLocationUri(String cityName) {
        return UriComponentsBuilder.fromPath(locationsPath)
                .queryParam("apikey", apiKey)
                .queryParam("q", cityName)
                .toUriString();
    }

    public Mono<String> getLocationKey(String cityName) {
        String locationUri = buildLocationUri(cityName);
        return webClient.get()
                .uri(locationUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new CustomClientException("Client error:" + clientResponse.statusCode())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new CustomServerException("Server error:" + clientResponse.statusCode())))
                .bodyToMono(String.class);
    }

    public Mono<WeatherInfoDTO> getWeatherInfo(String locationKey) {
        String completeForecastUri = baseApiUrl + forecastsPath.replace("{locationKey}", locationKey) + "?apikey=" + apiKey;
        return webClient.get()
                .uri(completeForecastUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new CustomClientException("Client error:" + clientResponse.statusCode())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new CustomServerException("Server error:" + clientResponse.statusCode())))
                .bodyToMono(WeatherInfoDTO.class);
    }
}