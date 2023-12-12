package com.meteosolutions.weatherapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value(value = "${api.base.url}")
    private String baseUrl;
    @Value(value = "${api.apiKey}")
    private String apiKey;
    @Value(value = "${api.locations.url}")
    private String locationsUrl;
    @Value(value = "${api.forecasts.url}")
    private String forecastsUrl;



    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .defaultHeader("apikey", apiKey)
                .build();
    }


}
