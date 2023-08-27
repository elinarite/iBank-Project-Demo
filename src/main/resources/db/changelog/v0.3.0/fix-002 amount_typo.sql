--liquibase formatted sql
--changeset Anton:v0.3.0-fix002

alter table ibank_schema.outcoming_transaction
    rename column money_ammount to money_amount;

alter table ibank_schema.credit_payments
    rename column payment_ammount to payment_amount;

alter table ibank_schema.credit_offer
    rename column credit_min_ammount to credit_min_amount;

alter table ibank_schema.credit_offer
    rename column credit_max_ammount to credit_max_amount;

alter table ibank_schema.card_account_data_limit
    rename column monthly_limit_ammount to monthly_limit_amount;

alter table ibank_schema.card_account_data_limit
    rename column daily_limit_ammount to daily_limit_amount;

