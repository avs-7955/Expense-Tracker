package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.dto.LoginTokenDTO;
import com.kyra.Expense.Tracker.dto.UserDTO;
import com.kyra.Expense.Tracker.dto.UserLoginDTO;
import com.kyra.Expense.Tracker.dto.UserSignUpDTO;
import com.kyra.Expense.Tracker.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${deploy.env}")
    private String deploymentEnv;

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid UserSignUpDTO signUpDTO) {
        UserDTO user = service.register(signUpDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginTokenDTO> login(@RequestBody @Valid UserLoginDTO loginDTO,
                                               HttpServletResponse response) {
        LoginTokenDTO tokens = service.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(("prod").equals(deploymentEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginTokenDTO> refresh(HttpServletRequest request) {
        if (Objects.isNull(request.getCookies())) {
            throw new AuthenticationServiceException("Refresh Token not present inside the cookies");
        }
        Optional<String> refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue);
        if (refreshToken.isEmpty()) {
            throw new AuthenticationServiceException("Refresh Token not present inside the cookies");
        }

        LoginTokenDTO tokens = service.refreshToken(refreshToken.get());

        return ResponseEntity.ok(tokens);
    }
}
