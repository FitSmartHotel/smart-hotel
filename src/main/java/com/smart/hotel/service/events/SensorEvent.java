package com.smart.hotel.service.events;

import com.smart.hotel.domain.NumberEntity;
import com.smart.hotel.service.dto.EventDTO;
import com.smart.hotel.service.dto.sensors.DistanceSensorDTO;
import com.smart.hotel.service.events.sensors.DistanceSensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;


@Getter
@AllArgsConstructor
@ToString
public class SensorEvent<T> implements ResolvableTypeProvider {

    private final SensorType sensorType;

    private final T sensorData;

    private final NumberEntity roomNumber;

    public enum SensorType {
        DISTANCE, DOOR
    }

    public static SensorEvent<DistanceSensor> fromDTO(EventDTO<DistanceSensorDTO> event, NumberEntity entity) {
        return new SensorEvent<>(
                SensorType.valueOf(event.getSensorType().name()),
                new DistanceSensor(DistanceSensor.Type.valueOf(event.getSensorData().getDistanceSensor().name()),
                        DistanceSensor.DistanceSensorEventType.valueOf(event.getSensorData().getEventType().name())),
                entity);
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(this.sensorData));
    }
}