package com.kyra.Expense.Tracker.converters;


import com.kyra.Expense.Tracker.converters.config.BaseEntityIgnoreConfig;
import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.dto.CategoryCreateDTO;
import com.kyra.Expense.Tracker.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseEntityIgnoreConfig.class)
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {

    @Override
    @Mapping(target = "user", ignore = true)
    Category toEntity(CategoryDTO dto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "systemGenerated", ignore = true)
    @Mapping(target = "active", ignore = true)
    Category toEntity(CategoryCreateDTO dto);
}

