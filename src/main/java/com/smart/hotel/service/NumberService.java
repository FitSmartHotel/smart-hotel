package com.smart.hotel.service;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.dto.NumberDTO;
import com.smart.hotel.service.exceptions.EntityNotFoundException;
import com.smart.hotel.service.exceptions.NumberAlreadyExistsException;
import com.smart.hotel.service.mapper.NumberMapper;
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

    private final UserService userService;

    private final NumberMapper numberMapper;

    @Transactional
    public NumberDTO createNumber(NumberDTO.CreateNumberDTO createNumberDTO) {
        numberRepository.findByNumber(createNumberDTO.getNumber()).ifPresent(n -> {throw new NumberAlreadyExistsException();});
        return new NumberDTO(numberRepository.save(numberMapper.createNumberDTOtoNumber(createNumberDTO)));
    }

    @Transactional(readOnly = true)
    public Optional<NumberDTO> getNumber(String number) {
        return numberRepository.findByNumber(number).map(NumberDTO::new);
    }

    @Transactional(readOnly = true)
    public List<NumberDTO> getNumbers() {
        return numberRepository.findAll().stream().map(NumberDTO::new).collect(Collectors.toUnmodifiableList());
    }

    public void deleteNumber(String number) {
        numberRepository.deleteByNumber(number);
    }

    @Transactional
    public void register(String number) {
        numberRepository.findByNumber(number).ifPresentOrElse(n -> n.setRegistered(true),
                () -> {throw new EntityNotFoundException(String.format("Number %s was not found", number));});
    }

    @Transactional
    public void assingUser(String number, NumberDTO.AssignUserDTO assignUserDTO) {
        numberRepository.findByNumber(number)
                .ifPresentOrElse(n -> n.setUser(userService.getUser(assignUserDTO.getUserId())),
                        () -> {throw new EntityNotFoundException("Number %s was not found");});
    }

    @Transactional
    public void unassingUser(String number) {
        numberRepository.findByNumber(number)
                .ifPresent(n -> n.setUser(null));
    }
}
