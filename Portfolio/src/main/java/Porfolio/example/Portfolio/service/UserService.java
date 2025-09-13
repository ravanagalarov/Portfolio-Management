package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.UserResponse;
import Porfolio.example.Portfolio.enums.Role;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);

    UserResponse updateUserRole(Long id, Role role);

}
