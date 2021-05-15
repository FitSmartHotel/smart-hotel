package com.smart.hotel.web.rest;

import com.smart.hotel.security.AuthoritiesConstants;
import com.smart.hotel.service.NumberManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/numbers")
@RequiredArgsConstructor
public class NumberManagementController {

    private final NumberManagementService numberManagementService;

    @PutMapping("{numberId}/management/lock")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\") or @numberAuthorizationService.userAssignedToNumber(#numberId)")
    public void lockNumber(@PathVariable String numberId) {
        numberManagementService.lockNumber(numberId);
    }

    @PutMapping("{numberId}/management/unlock")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\") or @numberAuthorizationService.userAssignedToNumber(#numberId)")
    public void unlockNumber(@PathVariable String numberId) {
        numberManagementService.unlockNumber(numberId);
    }

    @PutMapping("{numberId}/management/alarm")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\") or @numberAuthorizationService.userAssignedToNumber(#numberId)")
    public void unlockNumber(@PathVariable String numberId, @RequestParam("enable") boolean state) {
        numberManagementService.setAlarmState(numberId, state);
    }
}
