
--liquibase formatted sql

--changeset kyra:003-create-expense-table

CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    reference_id UUID NOT NULL UNIQUE,

    amount NUMERIC(12, 2) NOT NULL,
    expense_date DATE NOT NULL,
    description VARCHAR(255),
    payment_method VARCHAR(50),

    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_expense_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

    CONSTRAINT fk_expense_category
        FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);

