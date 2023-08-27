--liquibase formatted sql
--changeset anton:v0.2.0-fix004

ALTER TABLE  ibank_schema.card_account_data
    ALTER COLUMN card_account_number type varchar(20) using card_account_number::varchar(20);


