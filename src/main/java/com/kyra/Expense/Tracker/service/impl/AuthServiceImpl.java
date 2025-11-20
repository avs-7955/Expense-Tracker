package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.converters.UserMapper;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.UserDTO;
import com.kyra.Expense.Tracker.dto.UserLoginDTO;
import com.kyra.Expense.Tracker.dto.UserSignUpDTO;
import com.kyra.Expense.Tracker.enums.AuthProvider;
import com.kyra.Expense.Tracker.enums.Role;
import com.kyra.Expense.Tracker.exceptions.ConflictException;
import com.kyra.Expense.Tracker.security.jwt.JwtService;
import com.kyra.Expense.Tracker.service.AuthService;
import com.kyra.Expense.Tracker.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDTO register(@NonNull UserSignUpDTO signUpDTO) {
        User user = null;
        try {
            user = userService.getUserByEmail(signUpDTO.getEmail());
        } catch (Exception _) {

        }
        if (Objects.nonNull(user)) {
            throw new ConflictException("User", "email", signUpDTO.getEmail());
        }

        user = userMapper.toEntity(signUpDTO);
        user.setPasswordHash(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRole(Role.USER);
        user.setProvider(AuthProvider.LOCAL);
        User savedUser = userService.createUser(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public String login(@NonNull UserLoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }

}
