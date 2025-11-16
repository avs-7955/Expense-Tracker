
--liquibase formatted sql

--changeset kyra:002-create-categories

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    reference_id UUID NOT NULL UNIQUE,

    name VARCHAR(100) NOT NULL,
    description TEXT,
    icon VARCHAR(100),
    system_generated BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    user_id BIGINT,

    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_categories_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

    CONSTRAINT uk_categories_user_name UNIQUE (user_id, name)
);

