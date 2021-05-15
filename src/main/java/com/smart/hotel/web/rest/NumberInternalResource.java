package com.smart.hotel.web.rest;

import com.smart.hotel.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/numbers")
@RequiredArgsConstructor
public class NumberInternalResource {

    private final NumberService numberService;

    @PostMapping("{number}/register")
    public void register(@PathVariable String number) {
        numberService.register(number);
    }
}
