package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.AuthRequest;
import Porfolio.example.Portfolio.dtos.AuthResponse;
import Porfolio.example.Portfolio.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }
}













