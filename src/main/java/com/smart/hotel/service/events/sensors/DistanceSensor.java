package com.smart.hotel.service.events.sensors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DistanceSensor {

    private final Type type;

    private final DistanceSensorEventType eventType;

    public enum DistanceSensorEventType {
        TRIGGERED
    }

    public enum Type {
        LEFT, RIGHT
    }
}
