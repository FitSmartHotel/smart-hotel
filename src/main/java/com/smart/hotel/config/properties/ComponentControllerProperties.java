package com.smart.hotel.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "component-controller", ignoreUnknownFields = false)
public class ComponentControllerProperties {

    @NotNull
    public String url;
}
