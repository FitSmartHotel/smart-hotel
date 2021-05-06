package com.smart.hotel.service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart.hotel.domain.NumberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberDTO {

    private String number;

    private String level;

    private BigDecimal price;

    private int usersAmount;

    private boolean doorLocked;

    private boolean alarmEnabled;

    private boolean locked;

    private boolean registered;

    private boolean isAssigned;

    private String assignedUserLogin;

    public NumberDTO(NumberEntity number) {
        this.number = number.getNumber();
        level = number.getLevel();
        price = number.getPrice();
        usersAmount = number.getUsersAmount();
        doorLocked = number.isDoorLocked();
        alarmEnabled = number.isAlarmEnabled();
        registered = number.isRegistered();
        locked = number.isLocked();
        isAssigned = number.isAssinged();
        assignedUserLogin = isAssigned ? number.getUser().getLogin() : null;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateNumberDTO {

        @NotEmpty
        private String number;

        @NotEmpty
        private String level;

        @PositiveOrZero
        @NotNull
        private BigDecimal price;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AssignUserDTO {

        @PositiveOrZero
        @NotNull
        public Long userId;
    }

}
