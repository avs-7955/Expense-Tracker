
--liquibase formatted sql

--changeset kyra:001-user-starter-data context:dev
INSERT INTO users (
    reference_id,
    first_name,
    last_name,
    email,
    password_hash,
    role,
    provider,
    active,
    avatar_url,
    created_at,
    updated_at
) VALUES (
    '3b8f0c58-921f-4a6a-9e53-8e9df6d651cc',
    'System',
    'Admin',
    'admin@example.com',
    '$2a$10$MrAvp1LsAzbk9xp4s7gIhOHYZRcF4SrPRlelJHDTMjG1ufKSiu4vm', -- BCrypt hash of "admin123"
    'ADMIN',
    'LOCAL',
    TRUE,
    NULL,
    NOW(),
    NOW()
);

