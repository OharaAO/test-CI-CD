package com.ohara.corrida_colosseum.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountRequest {

    protected String firstName;
    protected String email;
    protected String phone;
    protected String password1;
    protected String password2;



}
