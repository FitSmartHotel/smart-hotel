package com.smart.hotel.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart.hotel.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    private String login;

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }
}
