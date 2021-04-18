package com.smart.hotel.service.events.sensors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoorSensor {

    private final EventType eventType;

    public enum EventType {
        OPENED, CLOSED
    }
}
