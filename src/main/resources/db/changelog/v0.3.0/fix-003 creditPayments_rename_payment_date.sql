--liquibase formatted sql
--changeset Anton:v0.3.0-fix003

alter table ibank_schema.credit_payments
    rename column payment_date to transaction_time;

