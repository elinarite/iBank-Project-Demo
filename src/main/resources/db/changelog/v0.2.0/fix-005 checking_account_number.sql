--liquibase formatted sql
--changeset anton:v0.2.0-fix005

ALTER TABLE ibank_schema.checking_account_data
    ALTER COLUMN account_number type varchar(20) using account_number::varchar(20);



