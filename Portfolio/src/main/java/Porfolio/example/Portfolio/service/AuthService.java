package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.AuthRequest;
import Porfolio.example.Portfolio.dtos.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);

    AuthResponse register(AuthRequest authRequest);

}
