package org.example.service;

import org.example.dto.UserDTO;

import java.util.List;

public interface UserService {

    void addNewUser(UserDTO userDto);

    UserDTO getUserById(Long id);
    String getUserView(Long userId);

    List<String> viewUsers();
}

