package com.smart.hotel.service;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.dto.NumberDTO;
import com.smart.hotel.service.mapper.NumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NumberService {

    private final NumberRepository numberRepository;

    private final NumberMapper numberMapper;

    public NumberDTO createNumber(NumberDTO.CreateNumberDTO createNumberDTO) {
        return new NumberDTO(numberRepository.save(numberMapper.createNumberDTOtoNumber(createNumberDTO)));
    }

    public Optional<NumberDTO> getNumber(String number) {
        return numberRepository.findByNumber(number).map(NumberDTO::new);
    }
}
