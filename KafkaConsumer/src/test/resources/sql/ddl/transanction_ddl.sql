DROP TABLE IF EXISTS bank_transaction;

CREATE TABLE bank_transaction(
origin_account_id VARCHAR(255) NOT NULL,
destination_account_id VARCHAR(255) NOT NULL,
transaction_type VARCHAR(255) NOT NULL,
money DECIMAL(10,2) NOT NULL
);
