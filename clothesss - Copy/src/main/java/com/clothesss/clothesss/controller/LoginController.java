package com.clothesss.clothesss.controller;
import com.clothesss.clothesss.Entity.LoginRequest;
import com.clothesss.clothesss.Entity.LoginResponse;
import com.clothesss.clothesss.Entity.User;
import com.clothesss.clothesss.Util.JwtUtil;
import com.clothesss.clothesss.exception.LoginFailedException;
import com.clothesss.clothesss.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        logger.info("Received login request for user: {}", loginRequest.getEmail());

        try {
            User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

            if (user != null) {
                String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                LoginResponse response = new LoginResponse("Login successful", user.getId(), token);
                return ResponseEntity.ok(response);
            } else {
                logger.warn("User not found or invalid credentials for login request: {}", loginRequest.getEmail());
                throw new UsernameNotFoundException("Invalid credentials");
            }

        } catch (UsernameNotFoundException e) {
            logger.warn("Login failed: {}", e.getMessage());
            throw new LoginFailedException("Invalid credentials");
        } catch (Exception e) {
            logger.error("Exception during login: {}", e.getMessage(), e);
            throw new LoginFailedException("Exception: " + e.getMessage());
        }
    }
}