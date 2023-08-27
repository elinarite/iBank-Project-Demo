--liquibase formatted sql
--changeset Anton:v0.3.0-fix007

CREATE TRIGGER set_exchange_rate_time
    BEFORE INSERT ON ibank_schema.exchange_rate
    FOR EACH ROW
EXECUTE FUNCTION exchange_rate_timestamp();
