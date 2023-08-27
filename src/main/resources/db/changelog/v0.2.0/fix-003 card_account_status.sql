--liquibase formatted sql
--changeset sergii:v0.2.0-fix003

ALTER TABLE card_account_status
ALTER COLUMN block_reason TYPE bigint
USING block_reason::bigint;

  ALTER TABLE card_account_status
RENAME COLUMN block_reason TO block_reason_id;


