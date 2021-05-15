package com.smart.hotel.clients.configuration;

import com.smart.hotel.clients.configuration.interceptors.LoggingInterceptor;
import com.smart.hotel.config.properties.ComponentControllerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@RequiredArgsConstructor
public class ComponentControllerRestClientConfiguration {

    private final ComponentControllerProperties componentControllerProperties;

    private final LoggingInterceptor loggingInterceptor;

    @Bean
    public RestTemplate componentControllerRestClient(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .interceptors(loggingInterceptor)
                .uriTemplateHandler(new DefaultUriBuilderFactory(componentControllerProperties.getUrl()))
                .build();
    }

}
