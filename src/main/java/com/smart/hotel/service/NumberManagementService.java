package com.smart.hotel.service;

import com.smart.hotel.clients.ComponentControllerClient;
import com.smart.hotel.domain.NumberEntity;
import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumberManagementService {

    private final NumberRepository numberRepository;

    private final ComponentControllerClient componentControllerClient;

    @Transactional
    public void lockNumber(String number) {
        log.info("Locking number {}", number);
        NumberEntity numberEntity = numberRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Number %s was not found", number)));

        componentControllerClient.lockNumber();

        numberEntity.setLocked(true);
    }

    @Transactional
    public void unlockNumber(String number) {
        log.info("Unlocking number {}", number);
        NumberEntity numberEntity = numberRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Number %s was not found", number)));

        componentControllerClient.unlockNumber();

        numberEntity.setLocked(false);
    }

    @Transactional
    public void setAlarmState(String number, boolean state) {
        numberRepository.findByNumber(number)
                .ifPresentOrElse(n -> n.setAlarmEnabled(state),
                        () -> {throw new EntityNotFoundException(String.format("Number %s was not found", number));});
    }
}
