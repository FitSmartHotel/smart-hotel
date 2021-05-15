package com.smart.hotel.service.dto.sensors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistanceSensorDTO implements SensorDTO {

    @NotNull
    private DistanceSensor distanceSensor;

    @NotNull
    public EventType eventType;

    public enum DistanceSensor {
        LEFT, RIGHT
    }
}
