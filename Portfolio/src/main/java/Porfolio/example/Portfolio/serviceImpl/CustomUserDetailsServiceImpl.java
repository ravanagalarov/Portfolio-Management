package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.security.CustomUserDetails;
import Porfolio.example.Portfolio.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
