--liquibase formatted sql
--changeset anton:v0.2.0-fix007

alter table ibank_schema.bank_data
    alter column id
        set generated always;

alter table ibank_schema.block_reason
    alter column id
        set generated always;

alter table ibank_schema.card_account_data
    alter column id
        set generated always;

alter table ibank_schema.card_account_data_limit
    alter column id
        set generated always;

alter table ibank_schema.card_account_status
    alter column id
        set generated always;

alter table ibank_schema.checking_account_data
    alter column id
        set generated always;

alter table ibank_schema.checking_account_status
    alter column id
        set generated always;

alter table ibank_schema.client_data
    alter column id
        set generated always;

alter table ibank_schema.country_data
    alter column id
        set generated always;

alter table ibank_schema.credit_issued
    alter column id
        set generated always;

alter table ibank_schema.credit_offer
    alter column id
        set generated always;

alter table ibank_schema.credit_payments
    alter column id
        set generated always;

alter table ibank_schema.credit_req
    alter column id
        set generated always;

alter table ibank_schema.credit_req_details
    alter column id
        set generated always;

alter table ibank_schema.currency_data
    alter column id
        set generated always;

alter table ibank_schema.exchange_rate
    alter column id
        set generated always;

alter table ibank_schema.exchange_ticker
    alter column id
        set generated always;

alter table ibank_schema.incoming_transaction
    alter column id
        set generated always;

alter table ibank_schema.manager_data
    alter column id
        set generated always;

alter table ibank_schema.outcoming_transaction
    alter column id
        set generated always;

