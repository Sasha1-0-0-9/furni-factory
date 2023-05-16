package com.example.furnifactory.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    // private String phoneNumber;

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getFirst_name(),
                user.getSurname(),
                user.getEmail()
                //user.getPhoneNumber()
        );
    }
}