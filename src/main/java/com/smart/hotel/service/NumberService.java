package com.smart.hotel.service;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.dto.NumberDTO;
import com.smart.hotel.service.mapper.NumberMapper;
import com.smart.hotel.web.rest.errors.NumberAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NumberService {

    private final NumberRepository numberRepository;

    private final NumberMapper numberMapper;

    public NumberDTO createNumber(NumberDTO.CreateNumberDTO createNumberDTO) {
        numberRepository.findByNumber(createNumberDTO.getNumber()).ifPresent(n -> {throw new NumberAlreadyExistsException();});
        return new NumberDTO(numberRepository.save(numberMapper.createNumberDTOtoNumber(createNumberDTO)));
    }

    public Optional<NumberDTO> getNumber(String number) {
        return numberRepository.findByNumber(number).map(NumberDTO::new);
    }

    public List<NumberDTO> getNumbers() {
        return numberRepository.findAll().stream().map(NumberDTO::new).collect(Collectors.toUnmodifiableList());
    }

    public void deleteNumber(String number) {
        numberRepository.deleteByNumber(number);
    }
}
