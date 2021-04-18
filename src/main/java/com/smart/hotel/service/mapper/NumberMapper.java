package com.smart.hotel.service.mapper;

import com.smart.hotel.domain.NumberEntity;
import com.smart.hotel.service.dto.NumberDTO;
import org.springframework.stereotype.Service;

@Service
public class NumberMapper {

    public NumberEntity createNumberDTOtoNumber(NumberDTO.CreateNumberDTO createNumberDTO) {
        return NumberEntity.builder()
                .number(createNumberDTO.getNumber())
                .level(createNumberDTO.getLevel())
                .price(createNumberDTO.getPrice())
                .usersAmount(0)
                .doorLocked(true)
                .alarmEnabled(false)
                .locked(false)
                .registered(false)
                .build();
    }
}
