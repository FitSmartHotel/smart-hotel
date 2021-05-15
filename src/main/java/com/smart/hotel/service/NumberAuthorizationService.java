package com.smart.hotel.service;

import com.smart.hotel.repository.NumberRepository;
import com.smart.hotel.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NumberAuthorizationService {

    private final NumberRepository numberRepository;

    public boolean userAssignedToNumber(String number) {
        return SecurityUtils.getCurrentUserLogin()
                .map(ul -> numberRepository.findByNumberAndUserLogin(number, ul).isPresent())
                .orElse(false);
    }
}
