
--liquibase formatted sql

--changeset kyra:001-create-user

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    reference_id UUID NOT NULL UNIQUE,

    first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(60) NOT NULL,
    role VARCHAR(20) NOT NULL,
    provider VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    avatar_url TEXT,

    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,

    CONSTRAINT uk_users_email UNIQUE (email)
);

