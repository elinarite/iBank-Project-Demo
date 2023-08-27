--changeset Anton:v0.3.0-fix006

CREATE OR REPLACE FUNCTION exchange_rate_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.rate_date := current_timestamp;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;