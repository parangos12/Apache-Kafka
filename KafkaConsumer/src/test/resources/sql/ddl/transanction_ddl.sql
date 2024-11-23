DROP TABLE IF EXISTS bank_transaction;

CREATE TABLE bank_transaction(
originAccountId VARCHAR(255) NOT NULL,
destinationAccountId VARCHAR(255) NOT NULL,
transactionType VARCHAR(255) NOT NULL,
money DECIMAL(10,2) NOT NULL
);
