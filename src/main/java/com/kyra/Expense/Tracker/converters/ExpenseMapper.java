package com.kyra.Expense.Tracker.converters;


import com.kyra.Expense.Tracker.converters.config.BaseEntityIgnoreConfig;
import com.kyra.Expense.Tracker.db.Expense;
import com.kyra.Expense.Tracker.dto.ExpenseCreateDTO;
import com.kyra.Expense.Tracker.dto.ExpenseDTO;
import com.kyra.Expense.Tracker.dto.ExpenseUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseEntityIgnoreConfig.class)
public interface ExpenseMapper extends BaseMapper<Expense, ExpenseDTO> {

    @Override
    @Mapping(target = "userReferenceId", source = "user.referenceId")
    @Mapping(target = "categoryReferenceId", source = "category.referenceId")
    ExpenseDTO toDTO(Expense entity);

    @Override
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    Expense toEntity(ExpenseDTO dto);

    @Mapping(target = "referenceId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category.referenceId", source = "categoryReferenceId")
    Expense toEntity(ExpenseCreateDTO dto);

    @Mapping(target = "referenceId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    Expense toEntity(ExpenseUpdateDTO dto);
}

