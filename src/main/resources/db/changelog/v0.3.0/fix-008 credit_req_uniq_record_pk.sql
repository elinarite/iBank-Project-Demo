--changeset Anton:v0.3.0-fix008

alter table ibank_schema.credit_req
    add constraint uniq_record_pk
        unique (credit_offer_id, credit_req_details_id);