package com.kyra.Expense.Tracker.converters.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig(
        uses = {},
        mappingInheritanceStrategy = org.mapstruct.MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
public interface BaseEntityIgnoreConfig {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void ignoreBaseFields(Object source, @MappingTarget Object target);
}

