
--liquibase formatted sql

--changeset kyra:003-expense-starter-data context: dev

INSERT INTO expenses (
    reference_id,
    amount,
    expense_date,
    description,
    payment_method,
    user_id,
    category_id,
    created_at,
    updated_at
) VALUES (
    'b3afa52c-6d6e-4fd5-8326-8b9484ab6156',
    249.99,
    '2025-01-16',
    'Dinner at Barbeque Nation',
    'CREDIT_CARD',
    1,
    1,
    NOW(),
    NOW()
);
