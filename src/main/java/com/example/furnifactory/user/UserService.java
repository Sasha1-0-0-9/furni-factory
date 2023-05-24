package com.example.furnifactory.user;

import com.example.furnifactory.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Long USER_ROLE_ID = 3L;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::from)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto create(UserCreateCommand command) {
        User user = new User();
        user.mapPrimitives(command);
        Role role = roleRepository.findById(USER_ROLE_ID)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return UserDto.from(userRepository.save(user));
    }

    public UserAuthDto auth(UserAuthCommand command) {
        User user = userRepository.findByEmail(command.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(command.getPassword());
        if (encoder.matches(user.getPassword(), command.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.generateTokenForUser(user);
        return new UserAuthDto(user.getId(), user.getFirst_name(), user.getSurname(), user.getEmail(), token);
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new RuntimeException("User not found");
                });

        userRepository.deleteById(id);
    }

    public UserDto updateRole(Long userId, RoleUpdateCommand command){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findAll()
                        .stream().filter(r -> r.getType().equals(command.getRole())).findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
        return UserDto.from(user);
    }
}
