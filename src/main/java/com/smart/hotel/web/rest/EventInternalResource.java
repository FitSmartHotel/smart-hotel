package com.smart.hotel.web.rest;

import com.smart.hotel.service.EventPublisherService;
import com.smart.hotel.service.dto.EventDTO;
import com.smart.hotel.service.dto.sensors.DistanceSensorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/internal/events")
@RequiredArgsConstructor
public class EventInternalResource {

    private final EventPublisherService eventPublisherService;

    @PostMapping("/distance")
    public void receiveDistanceSensorEvent(@RequestBody @Valid EventDTO<DistanceSensorDTO> event,
                                           @RequestParam String number) {
        eventPublisherService.publishDistanceSensorEvent(event, number);
    }

}
