--liquibase formatted sql
--changeset anton:v0.2.0-fix006

alter table ibank_schema.card_account_data
    rename column "isBlocked" to is_blocked;

alter table ibank_schema.checking_account_data
    rename column "isBlocked" to is_blocked;

