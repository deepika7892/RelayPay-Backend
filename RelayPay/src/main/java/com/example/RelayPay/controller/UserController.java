package com.example.RelayPay.controller;
import com.example.RelayPay.dto.LoginResponseDTO;
import com.example.RelayPay.dto.LoginRequest;
import com.example.RelayPay.dto.RegisterRequest;
import com.example.RelayPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.RelayPay.entity.User;
import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponseDTO loginUser(
            @RequestBody LoginRequest request
    ) {

        return userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }
}