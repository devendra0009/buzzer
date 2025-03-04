package com.davendra.buzzer.controller;

import com.davendra.buzzer.dto.request.LoginRequest;
import com.davendra.buzzer.dto.request.RegisterRequest;
import com.davendra.buzzer.dto.response.AuthResponse;
import com.davendra.buzzer.enums.GenderEnum;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestParam(name = "firstName", required = true) String firstName,
                                                     @RequestParam(name = "lastName", required = true) String lastName,
                                                     @RequestParam(name = "userName", required = true) String userName,
                                                     @RequestParam(name = "email", required = false) String email,
                                                     @RequestParam(name = "phone", required = false) String phone,
                                                     @RequestParam(name = "password", required = true) String password,
                                                     @RequestParam(name = "gender", required = true) GenderEnum gender,
                                                     @RequestPart(value = "profileImg", required = false) MultipartFile profileImg) throws IOException {

        RegisterRequest registerRequest = RegisterRequest.builder().firstName(firstName).lastName(lastName).userName(userName).email(email).phone(phone).password(password).gender(gender).profileImg(profileImg).build();
        AuthResponse registeredUser = userService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest user) {
        AuthResponse registeredUser = userService.loginUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
