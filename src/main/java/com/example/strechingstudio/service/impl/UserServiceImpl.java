package com.example.strechingstudio.service.impl;

import com.example.strechingstudio.dto.UserDto;
import com.example.strechingstudio.model.Role;
import com.example.strechingstudio.model.User;
import com.example.strechingstudio.repository.RoleRepository;
import com.example.strechingstudio.repository.UserRepository;
import com.example.strechingstudio.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Назначаем роль USER
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }
//    public void saveUser(UserDto userDto) {
//        User user = new User();
//        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
//        user.setEmail(userDto.getEmail());
//
//        //encrypt the password once we integrate spring security
//        //user.setPassword(userDto.getPassword());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);
//    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();

        // Разделяем имя пользователя
        String[] nameParts = user.getName().split(" ", 2);

        // Проверяем наличие имени и фамилии
        userDto.setFirstName(nameParts[0]); // Первое слово всегда есть
        userDto.setLastName(nameParts.length > 1 ? nameParts[1] : ""); // Если фамилии нет, оставляем пустой строкой

        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}