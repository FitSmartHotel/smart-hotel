package com.smart.hotel.service;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.dto.EventDTO;
import com.smart.hotel.service.dto.sensors.DistanceSensorDTO;
import com.smart.hotel.service.events.SensorEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPublisherService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final NumberRepository numberRepository;

    public void publishDistanceSensorEvent(EventDTO<DistanceSensorDTO> event, String number) {
        log.info("Received event: {}", event);
        var numberEntity = numberRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Number %s was not found", number)));

        applicationEventPublisher.publishEvent(SensorEvent.fromDTO(event, numberEntity));
    }
}
