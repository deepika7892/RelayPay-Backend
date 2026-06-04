package com.example.RelayPay.services;
import com.example.RelayPay.dto.LoginResponseDTO;
import com.example.RelayPay.jwt.JwtUtil;
import com.example.RelayPay.dto.RegisterRequest;
import com.example.RelayPay.entity.User;
import com.example.RelayPay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    // REGISTER USER
    public String registerUser(RegisterRequest request) {

        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN USER
    public LoginResponseDTO loginUser(
            String email,
            String password
    ) {

        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {

            return new LoginResponseDTO(
                    "User not found",
                    null
            );
        }

        boolean passwordMatch = passwordEncoder.matches(
                password,
                existingUser.getPassword()
        );

        if (!passwordMatch) {

            return new LoginResponseDTO(
                    "Invalid password",
                    null
            );
        }

        // Generate JWT Token
        String token = jwtUtil.generateToken(
                email,
                existingUser.getRole()
        );

        return new LoginResponseDTO(
                "Login successful",
                token
        );
    }
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}