package com.smart.hotel.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart.hotel.service.dto.sensors.SensorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO<T extends SensorDTO> {

    @NotNull
    private SensorType sensorType;

    @Valid
    private T sensorData;

    public enum SensorType {
        DISTANCE
    }
}
