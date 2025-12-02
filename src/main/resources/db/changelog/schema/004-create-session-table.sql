
--liquibase formatted sql

--changeset kyra:004-create-session-table

CREATE TABLE session (
    id BIGSERIAL PRIMARY KEY,

    reference_id UUID NOT NULL UNIQUE,

    created_by VARCHAR(255),
    updated_by VARCHAR(255),

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,

    user_id BIGINT,
    refresh_token TEXT,

    CONSTRAINT fk_session_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);