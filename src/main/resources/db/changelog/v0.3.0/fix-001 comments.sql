--liquibase formatted sql
--changeset Anton:v0.3.0-fix001

comment on column ibank_schema.credit_issued.credit_issued_date is 'Дата выдачи кредита';
comment on column ibank_schema.credit_issued.credit_next_payment is 'Дата следующего платежа';

