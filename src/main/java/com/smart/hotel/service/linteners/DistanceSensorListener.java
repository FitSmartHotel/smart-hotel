package com.smart.hotel.service.linteners;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.events.SensorEvent;
import com.smart.hotel.service.events.sensors.DistanceSensor;
import com.smart.hotel.service.events.sensors.DoorSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.BlockingArrayQueue;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.smart.hotel.service.events.sensors.DistanceSensor.Type.LEFT;
import static com.smart.hotel.service.events.sensors.DistanceSensor.Type.RIGHT;

@Slf4j
@Component
@RequiredArgsConstructor
public class DistanceSensorListener {

    private final NumberRepository numberRepository;

    private final BlockingQueue<DistanceSensor> queue = new BlockingArrayQueue<>();

    @Async
    @EventListener
    @Transactional
    public void handleDistanceSensorEvent(SensorEvent<DistanceSensor> event) throws InterruptedException {
        DistanceSensor eventSensor = event.getSensorData();
        DistanceSensor lastSensor = queue.peek();
        if(lastSensor != null && lastSensor.getType().equals(LEFT) && eventSensor.getType().equals(RIGHT)) {
            log.info("Incrementing users count in room {}", event.getRoomNumber());
            numberRepository.incrementUsersAmount(event.getRoomNumber().getNumber());
            queue.poll(1, TimeUnit.SECONDS);
        } else if(lastSensor != null && lastSensor.getType().equals(RIGHT) && eventSensor.getType().equals(LEFT)) {
            log.info("Decrementing users count in room {}", event.getRoomNumber());
            numberRepository.decrementUsersAmount(event.getRoomNumber().getNumber());
            queue.poll(1, TimeUnit.SECONDS);
        }
        else {
            log.info("Enqueueing event {}", event.getSensorData());
            queue.add(event.getSensorData());
        }
    }

    @Async
    @EventListener
    public void handleDoorSensorEvent(SensorEvent<DoorSensor> doorSensorEvent) {
    }
}
