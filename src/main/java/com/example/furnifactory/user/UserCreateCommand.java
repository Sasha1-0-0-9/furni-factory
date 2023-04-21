package com.example.furnifactory.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateCommand {
    private String first_name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
   // private String phoneNumber;
    private String password;
}
