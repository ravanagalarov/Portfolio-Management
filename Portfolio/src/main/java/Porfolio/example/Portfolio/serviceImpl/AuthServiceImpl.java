package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.dtos.AuthRequest;
import Porfolio.example.Portfolio.dtos.AuthResponse;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.enums.Role;
import Porfolio.example.Portfolio.exceptionHandling.UserAlreadyExistsException;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.security.CustomUserDetails;
import Porfolio.example.Portfolio.security.JwtUtil;
import Porfolio.example.Portfolio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final PortfolioRepository portfolioRepository;


    @Override
    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (authRequest.getUsername(), authRequest.getPassword()));

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found "));

        CustomUserDetails userDetails = mapper.map(user, CustomUserDetails.class);
        String jwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(jwt, userDetails.getUsername(), user.getRole());

    }

    @Override
    @Transactional
    public AuthResponse register(AuthRequest authRequest) {
        if (userRepository.existsByUsername(authRequest.getUsername())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        Portfolio portfolio = new Portfolio();
        portfolio.setUser(user);
        portfolioRepository.save(portfolio);

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(jwt, user.getUsername(), user.getRole());
    }
}
