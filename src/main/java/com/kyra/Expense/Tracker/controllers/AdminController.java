package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.converters.UserMapper;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.UserDTO;
import com.kyra.Expense.Tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PatchMapping("/users/{referenceId}/role/admin")
    public ResponseEntity<UserDTO> makeAdmin(@PathVariable UUID referenceId) {
        User user = userService.makeUserAdmin(referenceId);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @GetMapping("/users/admins")
    public ResponseEntity<List<UserDTO>> listAdmins() {
        List<User> users = userService.listAllAdmins();
        return ResponseEntity.ok(userMapper.toDTOs(users));
    }
}
