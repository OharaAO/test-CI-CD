package com.ohara.corrida_colosseum.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {
    protected String oldPassword;
    protected String newPassword1;

    protected String newPassword2;
}
