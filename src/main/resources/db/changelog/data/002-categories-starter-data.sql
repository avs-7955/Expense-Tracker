
--liquibase formatted sql

--changeset kyra:002-categories-starter-data

INSERT INTO categories
(reference_id, name, description, icon, system_generated, active, user_id, created_at, updated_at)
VALUES
('a1f8c39a-2cd9-4a59-bc77-062b7e456001', 'Food', 'Food and dining expenses', '🍔', TRUE, TRUE, NULL, NOW(), NOW()),
('05b57d8b-6d55-4fe4-8b79-633889da092a', 'Travel', 'Travel and commute expenses', '✈️', TRUE, TRUE, NULL, NOW(), NOW()),
('7d1b8653-11cf-48c3-a676-17c7949e199e', 'Bills', 'Utility bills and recurring expenses', '💡', TRUE, TRUE, NULL, NOW(), NOW()),
('cd5b90e0-9da4-4d12-afcf-ab65b5390c11', 'Shopping', 'Shopping and lifestyle expenses', '🛍️', TRUE, TRUE, NULL, NOW(), NOW()),
('7f2e3f8b-7cbe-4cd4-9f17-02dfe17ede45', 'Groceries', 'Daily household groceries', '🛒', TRUE, TRUE, NULL, NOW(), NOW()),
('2190c46a-9870-46bd-b864-ad351be0a18b', 'Entertainment', 'Movies, events, subscriptions', '🎬', TRUE, TRUE, NULL, NOW(), NOW()),
('9cd5f90e-c23d-43ea-a173-af1e7eaba91a', 'Health', 'Medicine, doctor visits, etc.', '💊', TRUE, TRUE, NULL, NOW(), NOW()),
('3e5f9df8-aaaa-400a-ae1d-ce6db404a572', 'Misc', 'Miscellaneous expenses', '📦', TRUE, TRUE, NULL, NOW(), NOW());
