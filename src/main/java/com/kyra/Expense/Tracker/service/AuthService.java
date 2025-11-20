package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.dto.UserDTO;
import com.kyra.Expense.Tracker.dto.UserLoginDTO;
import com.kyra.Expense.Tracker.dto.UserSignUpDTO;
import lombok.NonNull;

public interface AuthService {
    UserDTO register(@NonNull UserSignUpDTO signUpDTO);

    String login(@NonNull UserLoginDTO loginDTO);
}
