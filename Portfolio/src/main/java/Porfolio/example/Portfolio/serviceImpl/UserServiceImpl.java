package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.dtos.UserResponse;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.enums.Role;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setRole(role);
        User updated = userRepository.save(user);
        return mapper.map(updated, UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
