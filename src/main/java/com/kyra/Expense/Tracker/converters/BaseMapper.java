package com.kyra.Expense.Tracker.converters;

import org.mapstruct.Mapping;

import java.util.List;

public interface BaseMapper<E, D> {

    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTOs(List<E> entities);

}


