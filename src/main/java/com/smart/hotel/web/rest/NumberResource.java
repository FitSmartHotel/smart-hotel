package com.smart.hotel.web.rest;

import com.smart.hotel.security.AuthoritiesConstants;
import com.smart.hotel.service.NumberService;
import com.smart.hotel.service.dto.NumberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/numbers")
@RequiredArgsConstructor
public class NumberResource {

    private final NumberService numberService;

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public NumberDTO createNumber(@Valid @RequestBody NumberDTO.CreateNumberDTO createNumberDTO) {
        return numberService.createNumber(createNumberDTO);
    }

    @GetMapping("{number}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<NumberDTO> getNumber(@PathVariable String number) {
        return ResponseUtil.wrapOrNotFound(numberService.getNumber(number));
    }

    @DeleteMapping("{number}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void deleteNumber(@PathVariable String number) {
        numberService.deleteNumber(number);
    }

    @GetMapping("assigned")
    public List<NumberDTO> getNumbersAssigned() {
        return numberService.getNumberAssigned();
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<NumberDTO> getNumbers() {
        return numberService.getNumbers();
    }


    @PostMapping("{number}/assign")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void assignUser(@PathVariable String number,
                           @Valid @RequestBody NumberDTO.AssignUserDTO assignUserDTO) {
        numberService.assingUser(number, assignUserDTO);
    }

    @PutMapping("{number}/unassing")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void assignUser(@PathVariable String number) {
        numberService.unassingUser(number);
    }

}
