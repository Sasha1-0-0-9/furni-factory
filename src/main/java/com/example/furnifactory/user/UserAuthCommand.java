package com.example.furnifactory.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthCommand {
    private String email;
    private String password;
}
