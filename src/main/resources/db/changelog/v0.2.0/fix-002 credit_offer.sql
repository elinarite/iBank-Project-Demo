--liquibase formatted sql
--changeset anton:v0.2.0-fix002

alter table ibank_schema.credit_offer
    drop column credit_req;


