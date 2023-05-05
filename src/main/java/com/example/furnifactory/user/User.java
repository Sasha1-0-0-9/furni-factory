package com.example.furnifactory.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Role role;
    //todo role, login, color, manufacture controller,

    public User mapPrimitives(UserCreateCommand command) {
        this.first_name = command.getFirst_name();
        this.surname = command.getSurname();
        this.email = command.getEmail();
        this.dateOfBirth = LocalDate.of(2000, 1, 1);
        //this.dateOfBirth = command.getDateOfBirth();
        // this.phoneNumber = command.getPhoneNumber();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(command.getPassword());
        this.role = command.getRole();
        return this;
    }
}
