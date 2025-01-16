package com.example.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class RegisterRequest {

    private String username;


    private String password;

//    private String firstName;

//    private String lastName;

    private String email;


}