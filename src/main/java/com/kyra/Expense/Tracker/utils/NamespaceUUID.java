package com.kyra.Expense.Tracker.utils;

import lombok.Getter;

import java.util.UUID;

@Getter
public enum NamespaceUUID {

    USER(UUID.fromString("8354f2dd-f085-4f98-9c9f-9e62e4f9ab4b")),
    EXPENSE(UUID.fromString("a1e064c9-1033-4c96-a5cd-3a76315a63ea"));

    private final UUID namespace;

    NamespaceUUID(UUID uuid) {
        this.namespace = uuid;
    }
}
