package com.example.furnifactory.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::from)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //todo filters
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
        return UserDto.from(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new RuntimeException("User not found");
                });

        userRepository.deleteById(id);
    }

    //todo update and other methods

}