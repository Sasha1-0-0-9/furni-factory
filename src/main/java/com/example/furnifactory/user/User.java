package com.example.furnifactory.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String first_name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    //    private String phoneNumber;
    //todo password encryption
    private String password;
    //todo role

    public User mapPrimitives(UserCreateCommand command) {
        this.first_name = command.getFirst_name();
        this.surname = command.getSurname();
        this.email = command.getEmail();
        this.dateOfBirth = LocalDate.of(2000, 1, 1);
        //this.dateOfBirth = command.getDateOfBirth();
        // this.phoneNumber = command.getPhoneNumber();
        this.password = command.getPassword();
        return this;
    }
}
