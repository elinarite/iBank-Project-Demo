--liquibase formatted sql
--changeset Anton:v0.3.0-fix005

-- Triggers
CREATE TRIGGER set_transaction_time
    BEFORE INSERT ON ibank_schema.outcoming_transaction
    FOR EACH ROW
EXECUTE FUNCTION transactions_timestamp();

CREATE TRIGGER set_transaction_time
    BEFORE INSERT ON ibank_schema.incoming_transaction
    FOR EACH ROW
EXECUTE FUNCTION transactions_timestamp();

CREATE TRIGGER set_transaction_time
    BEFORE INSERT ON ibank_schema.credit_payments
    FOR EACH ROW
EXECUTE FUNCTION transactions_timestamp();
