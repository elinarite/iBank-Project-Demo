--changeset Anton:v0.3.0-fix004

CREATE OR REPLACE FUNCTION transactions_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.transaction_time := current_timestamp;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;
