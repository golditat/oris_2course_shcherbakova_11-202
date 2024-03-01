package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.entity.UserEntity;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository repository;
    @Override
    public void addNewUser(UserDTO userDto) {
        UserEntity userEntity = UserEntity.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthday(userDto.getBirthday())
                .email(userDto.getEmail())
                .build();
        repository.save(userEntity);

    }
    @Override
    public String getUserView(Long userId) {
        UserDTO userDto = getUserById(userId);
        return String.format("FirstName: %s,  LastName: %s, Birthday: %s, EMAIL: %s",
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthday(),
                userDto.getEmail());
    }
    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = repository.getOne(id);
        return UserDTO.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .birthday(userEntity.getBirthday())
                .email(userEntity.getEmail())
                .build();

    }

    @Override
    public List<String> viewUsers() {
        List<String> userDTOs = new ArrayList<>();
        List<UserEntity> userEntities = repository.findAll();
        for (UserEntity user : userEntities) {
            Long userId = user.getId();
            userDTOs.add(getUserView(userId));
        }
        return userDTOs;
    }
}
