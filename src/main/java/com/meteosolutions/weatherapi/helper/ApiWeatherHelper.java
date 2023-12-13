package com.meteosolutions.weatherapi.helper;

import com.meteosolutions.weatherapi.dto.DailyForecastDTO;
import com.meteosolutions.weatherapi.dto.WeatherCityDTO;
import com.meteosolutions.weatherapi.exception.CustomClientException;
import com.meteosolutions.weatherapi.exception.CustomServerException;
import lombok.extern.slf4j.Slf4j;
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
    private String cityName;

    private String baseApiUrl;
    private String locationsPath;
    private String forecastsPath;

    private String apiKey;


    public ApiWeatherHelper(WebClient.Builder webClientBuilder,
                            @Value("${user.base.name}") String cityName,
                            @Value("${api.base.url}") String baseApiUrl,
                            @Value("${api.locations.url}") String locationsPath,
                            @Value("${api.forecasts.url}") String forecastsPath,
                            @Value("${api.apiKey}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(baseApiUrl).build();
        this.cityName = cityName;
        this.baseApiUrl = baseApiUrl;
        this.locationsPath = locationsPath;
        this.forecastsPath = forecastsPath;
        this.apiKey = apiKey;


        log.info("Base API URL: {}", baseApiUrl);
        log.info("Locations Path: {}", locationsPath);
        log.info("Forecasts Path: {}", forecastsPath);
        log.info("API Key apiKey : {} ;", apiKey);
        log.info("Location Query Param : {};", cityName);

    }

    private String buildLocationUri(String cityName) {
        return UriComponentsBuilder.fromPath(locationsPath)
                .queryParam("apikey", apiKey)
                .queryParam("q", cityName)
                .toUriString();
    }

    public Mono<WeatherCityDTO> getWeatherInfoByCity(String cityName) {
        return getLocationKey(cityName)
                .flatMap(locationKey -> getWeatherInfo(locationKey)
                        .map(headline -> {
                            WeatherCityDTO weatherCityDTO = new WeatherCityDTO();
                            weatherCityDTO.setCityKey(locationKey);
                            weatherCityDTO.setCityName(cityName);
                            weatherCityDTO.setHeadline(headline.getHeadline());
                            return weatherCityDTO;
                        })
                )
                .onErrorResume(error -> Mono.error(new CustomClientException("Failed to fetch weather info for city: " + cityName)));
    }

    public Mono<String> getLocationKey(String cityName) {
        String locationUri = buildLocationUri(cityName);
        log.debug("Getting location key for city '{}'. URL: {}", cityName, locationUri);
        return webClient.get()
                .uri(locationUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new CustomClientException("Client error:" + clientResponse.statusCode())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new CustomServerException("Server error:" + clientResponse.statusCode())))
                .bodyToMono(String.class);
    }


    public Mono<WeatherCityDTO> getWeatherInfo(String locationKey) {
        String completeForecastUri = baseApiUrl + forecastsPath.replace("{locationKey}", locationKey) + "?apikey=" + apiKey;
        return webClient.get()
                .uri(completeForecastUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new CustomClientException("Client error:" + clientResponse.statusCode())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new CustomServerException("Server error:" + clientResponse.statusCode())))
                .bodyToMono(WeatherCityDTO.class);
    }
    public Mono<DailyForecastDTO> getDailyForecast(String cityKey) {
        String completeDailyForecastUri = baseApiUrl + forecastsPath.replace("{locationKey}", cityKey) + "?apikey=" + apiKey;
        return webClient.get()
                .uri(completeDailyForecastUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new CustomClientException("Client error:" + clientResponse.statusCode())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new CustomServerException("Server error:" + clientResponse.statusCode())))
                .bodyToMono(DailyForecastDTO.class);
    }
}