package com.kyra.Expense.Tracker.converters;

import com.kyra.Expense.Tracker.converters.config.BaseEntityIgnoreConfig;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.UserCreateDTO;
import com.kyra.Expense.Tracker.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseEntityIgnoreConfig.class)
public interface UserMapper extends BaseMapper<User, UserDTO> {
    @Override
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserDTO dto);

    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toEntity(UserCreateDTO dto);
}

