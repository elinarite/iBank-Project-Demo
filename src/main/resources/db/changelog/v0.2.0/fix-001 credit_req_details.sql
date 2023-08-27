--liquibase formatted sql
--changeset sergii:v0.2.0-fix001

alter table credit_req_details
    rename column column_name to credit_req_description;

