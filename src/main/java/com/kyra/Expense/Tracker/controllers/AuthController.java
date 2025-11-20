package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.dto.UserDTO;
import com.kyra.Expense.Tracker.dto.UserLoginDTO;
import com.kyra.Expense.Tracker.dto.UserSignUpDTO;
import com.kyra.Expense.Tracker.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid UserSignUpDTO signUpDTO) {
        UserDTO user = service.register(signUpDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO loginDTO,
                                        HttpServletResponse response) {
        String token = service.login(loginDTO);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
