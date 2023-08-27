--liquibase formatted sql
--changeset Anton:v0.3.0-ddl splitStatements:false

create table if not exists ibank_schema.currency_data
(
    id            bigint generated always as identity
        constraint currency_data_pk
            primary key,
    currency_code varchar(3)  not null
        constraint currency_code_uniq
            unique,
    currency_name varchar(24) not null
        constraint currency_name_uniq
            unique
);

comment on table ibank_schema.currency_data is 'Каталог валют';

comment on column ibank_schema.currency_data.currency_code is 'Код валюты';

comment on column ibank_schema.currency_data.currency_name is 'Название валюты';

alter table ibank_schema.currency_data
    owner to postgres;

create table if not exists ibank_schema.exchange_ticker
(
    id            bigint generated always as identity
        constraint exchange_ticker_pk
            primary key,
    ticker_name   varchar(15) not null
        constraint exchange_ticker_pk2
            unique,
    currency_from bigint      not null
        constraint currency_from_fk
            references ibank_schema.currency_data,
    currency_to   bigint      not null
        constraint currency_to_fk
            references ibank_schema.currency_data
);

comment on table ibank_schema.exchange_ticker is 'Каталог валютных тикеров';

comment on column ibank_schema.exchange_ticker.ticker_name is 'Тикер валютной пары';

comment on column ibank_schema.exchange_ticker.currency_from is 'Базовая валюта';

comment on column ibank_schema.exchange_ticker.currency_to is 'Котируемая валюта';

comment on constraint currency_to_fk on ibank_schema.exchange_ticker is 'Link to current ID';

alter table ibank_schema.exchange_ticker
    owner to postgres;

create table if not exists ibank_schema.exchange_rate
(
    id          bigint generated always as identity
        constraint "exchange_rate _pk"
            primary key,
    exch_ticker bigint  not null
        constraint exchange_rate_fk
            references ibank_schema.exchange_ticker,
    rate        numeric not null,
    rate_date   timestamp
);

comment on table ibank_schema.exchange_rate is 'Архив курсов валют';

comment on column ibank_schema.exchange_rate.exch_ticker is 'Тикер валютной пары';

comment on column ibank_schema.exchange_rate.rate is 'Курс тикера';

comment on column ibank_schema.exchange_rate.rate_date is 'Timestamp курса';

alter table ibank_schema.exchange_rate
    owner to postgres;

create table if not exists ibank_schema.country_data
(
    id               bigint generated always as identity
        constraint country_data_pk
            primary key,
    country_name     varchar(255) not null
        constraint country_name_uniq
            unique,
    country_code     varchar(3)   not null,
    country_currency bigint       not null
        constraint country_data_currency_data_id_fk
            references ibank_schema.currency_data
);

comment on table ibank_schema.country_data is 'Каталог стран';

comment on column ibank_schema.country_data.country_name is 'Название страны';

comment on column ibank_schema.country_data.country_code is 'Код страны (ISO 3166-1 Alpha-3)';

comment on column ibank_schema.country_data.country_currency is 'Валюта страны';

alter table ibank_schema.country_data
    owner to postgres;

create table if not exists ibank_schema.incoming_transaction
(
    id                       bigint generated always as identity
        constraint incoming_transaction_pk
            primary key,
    recipient_account_number varchar(20) not null,
    recipient_name           varchar(255),
    recipient_surname        varchar(255),
    sender_name              varchar(255),
    sender_surname           varchar(255),
    sender_bank_swift        varchar(11),
    sender_account_number    varchar(20),
    money_amount            numeric,
    currency_code            varchar(3),
    payment_purpose          varchar(255),
    transaction_time         timestamp   not null
);

comment on table ibank_schema.incoming_transaction is 'Входящие транзакции';

comment on column ibank_schema.incoming_transaction.recipient_account_number is 'Счет получателя';

comment on column ibank_schema.incoming_transaction.recipient_name is 'Имя получателя';

comment on column ibank_schema.incoming_transaction.recipient_surname is 'Фамилия получателя';

comment on column ibank_schema.incoming_transaction.sender_name is 'Имя отправителя';

comment on column ibank_schema.incoming_transaction.sender_surname is 'Фамилия отправителя';

comment on column ibank_schema.incoming_transaction.sender_bank_swift is 'SWIFT банка-отправителя';

comment on column ibank_schema.incoming_transaction.sender_account_number is 'Счет отправителя';

comment on column ibank_schema.incoming_transaction.money_amount is 'Сумма перевода';

comment on column ibank_schema.incoming_transaction.currency_code is 'Валюта перевода';

comment on column ibank_schema.incoming_transaction.payment_purpose is 'Назначение платежа';

comment on column ibank_schema.incoming_transaction.transaction_time is 'Timestamp транзакции';

alter table ibank_schema.incoming_transaction
    owner to postgres;

create table if not exists ibank_schema.manager_data
(
    id                               bigint generated always as identity
        constraint manager_data_pk
            primary key,
    manager_name                     varchar(255) not null,
    manager_middlename               varchar(255),
    manager_surname                  varchar(255) not null,
    manager_birthday                 date         not null,
    manager_gender                   char         not null,
    manager_passport                 varchar(50)  not null
        constraint manager_data_pk2
            unique,
    manager_passport_iss_date        date         not null,
    manager_passport_exp_date        date         not null,
    manager_country_id               bigint       not null
        constraint manager_citizenship_fk
            references ibank_schema.country_data,
    manager_phone                    varchar(20)  not null
        constraint manager_data_pk3
            unique,
    manager_email                    varchar(100) not null
        constraint manager_data_pk4
            unique,
    manager_hiring_date              timestamp    not null,
    manager_dismiss_date             timestamp,
    manager_passport_department_code varchar(10)  not null,
    manager_adress                   varchar(255) not null
);

comment on table ibank_schema.manager_data is 'Менеджер - информация';

comment on column ibank_schema.manager_data.manager_name is 'Имя';

comment on column ibank_schema.manager_data.manager_middlename is 'Отчество';

comment on column ibank_schema.manager_data.manager_surname is 'Фамилия';

comment on column ibank_schema.manager_data.manager_birthday is 'Дата рождения';

comment on column ibank_schema.manager_data.manager_gender is 'Пол';

comment on column ibank_schema.manager_data.manager_passport is 'Серия и номер паспорта';

comment on column ibank_schema.manager_data.manager_passport_iss_date is 'Дата выдачи паспорта ';

comment on column ibank_schema.manager_data.manager_passport_exp_date is 'Срок окончания паспорта';

comment on column ibank_schema.manager_data.manager_country_id is 'Гражданство';

comment on column ibank_schema.manager_data.manager_phone is 'Номер телефона';

comment on column ibank_schema.manager_data.manager_email is 'Электронная почта';

comment on column ibank_schema.manager_data.manager_hiring_date is 'Дата найма';

comment on column ibank_schema.manager_data.manager_dismiss_date is 'Дата увольнения';

comment on column ibank_schema.manager_data.manager_passport_department_code is 'Код подразделения выд. паспорт';

comment on column ibank_schema.manager_data.manager_adress is 'Адрес ';

alter table ibank_schema.manager_data
    owner to postgres;

create table if not exists ibank_schema.client_data
(
    id                              bigint generated always as identity
        constraint client_data_pk
            primary key,
    client_name                     varchar(255) not null,
    client_surname                  varchar(255) not null,
    client_middlename               varchar(255),
    client_gender                   char         not null,
    client_birthdate                date         not null,
    client_country_id               bigint       not null
        constraint client_country_fk
            references ibank_schema.country_data
            on delete restrict,
    client_address                  varchar(255) not null,
    client_email                    varchar(100) not null,
    client_phone                    varchar(20)  not null,
    client_manager_id               bigint       not null
        constraint client_manager_fk
            references ibank_schema.manager_data
            on delete restrict,
    client_passport                 varchar(50)  not null
        constraint client_data_passport
            unique,
    client_passport_issue_date      date         not null,
    client_passport_department_code varchar(10)  not null,
    client_passport_exp_date        date         not null,
    constraint client_data_uk_email_phone
        unique (client_email, client_phone)
);

comment on table ibank_schema.client_data is 'Клиент - информация';

comment on column ibank_schema.client_data.client_name is 'Имя';

comment on column ibank_schema.client_data.client_surname is 'Фамилия';

comment on column ibank_schema.client_data.client_middlename is 'Отчество';

comment on column ibank_schema.client_data.client_gender is 'Пол';

comment on column ibank_schema.client_data.client_birthdate is 'Дата рождения';

comment on column ibank_schema.client_data.client_country_id is 'Гражданство';

comment on column ibank_schema.client_data.client_address is 'Адрес';

comment on column ibank_schema.client_data.client_email is 'Эл. адрес почты';

comment on column ibank_schema.client_data.client_phone is 'Номер телефона';

comment on column ibank_schema.client_data.client_manager_id is 'Менеджер клиента';

comment on column ibank_schema.client_data.client_passport is 'Серия и номер паспорта';

comment on column ibank_schema.client_data.client_passport_issue_date is 'Дата выдачи пасспорта';

comment on column ibank_schema.client_data.client_passport_department_code is 'Код подразделения выд. паспорт';

comment on column ibank_schema.client_data.client_passport_exp_date is 'Срок действия паспорта';

alter table ibank_schema.client_data
    owner to postgres;

create table if not exists ibank_schema.checking_account_data
(
    id                  bigint generated always as identity
        constraint account_data_pk
            primary key,
    account_open_date   timestamp             not null,
    account_close_date  timestamp,
    account_client_id   bigint                not null
        constraint account_client_id_fk
            references ibank_schema.client_data,
    account_currency_id bigint                not null
        constraint account_currency_data_id_fk
            references ibank_schema.currency_data,
    account_balance     numeric               not null,
    account_number      varchar(20)           not null
        constraint account_number_pk2
            unique,
    is_blocked          boolean default false not null
);

comment on table ibank_schema.checking_account_data is 'Текущий счет';

comment on column ibank_schema.checking_account_data.account_open_date is 'Дата открытия счета';

comment on column ibank_schema.checking_account_data.account_close_date is 'Дата закрытия счета';

comment on column ibank_schema.checking_account_data.account_client_id is 'ID Клиента';

comment on column ibank_schema.checking_account_data.account_currency_id is 'Валюта счета';

comment on column ibank_schema.checking_account_data.account_balance is 'Баланс счета (не меньше 0)';

comment on column ibank_schema.checking_account_data.account_number is 'Номер счета';

comment on column ibank_schema.checking_account_data.is_blocked is 'Счет заблокирован?';

alter table ibank_schema.checking_account_data
    owner to postgres;

create table if not exists ibank_schema.card_account_data
(
    id                       bigint generated always as identity
        constraint card_id_pk
            primary key,
    card_number              char(16)              not null
        constraint card_number_pk
            unique,
    card_account_number      varchar(20)           not null
        constraint card_account_number_pk
            unique,
    card_issue_date          timestamp             not null,
    card_expiration_date     timestamp             not null,
    card_account_currency_id bigint                not null
        constraint card_account_currency_fk
            references ibank_schema.currency_data,
    card_account_balance     numeric default 0     not null,
    is_blocked               boolean default false not null,
    account_client_id        bigint                not null
        constraint card_account_data_client_data_id_fk
            references ibank_schema.client_data
);

comment on table ibank_schema.card_account_data is 'Карточный счет';

comment on column ibank_schema.card_account_data.card_number is 'Номер карты';

comment on column ibank_schema.card_account_data.card_account_number is 'Номер аккаунта';

comment on column ibank_schema.card_account_data.card_issue_date is 'Дата выпуска карты';

comment on column ibank_schema.card_account_data.card_expiration_date is 'Срок действия карты';

comment on column ibank_schema.card_account_data.card_account_currency_id is 'Валюта счета';

comment on column ibank_schema.card_account_data.card_account_balance is 'Баланс счета';

comment on column ibank_schema.card_account_data.is_blocked is 'Карта заблокирована?';

comment on column ibank_schema.card_account_data.account_client_id is 'ID Клиента';

alter table ibank_schema.card_account_data
    owner to postgres;

create table if not exists ibank_schema.bank_data
(
    id           bigint generated always as identity
        constraint bank_data_pk
            primary key,
    bank_name    varchar(255) not null,
    bank_address varchar(255),
    bank_country bigint       not null
        constraint bank_country_fk
            references ibank_schema.country_data,
    bank_iban    varchar(34)  not null
        constraint bank_data_pk3
            unique,
    bank_swift   varchar(11)  not null
        constraint bank_data_pk2
            unique
);

comment on table ibank_schema.bank_data is 'Каталог банков';

comment on column ibank_schema.bank_data.bank_name is 'Название банка';

comment on column ibank_schema.bank_data.bank_address is 'Адрес банка';

comment on column ibank_schema.bank_data.bank_country is 'Страна банка';

comment on column ibank_schema.bank_data.bank_iban is 'IBAN';

comment on column ibank_schema.bank_data.bank_swift is 'SWIFT / БИК';

alter table ibank_schema.bank_data
    owner to postgres;

create table if not exists ibank_schema.outcoming_transaction
(
    id                       bigint generated always as identity
        constraint outcoming_transaction_pk
            primary key,
    sender_name              varchar(255) not null,
    sender_surname           varchar(255) not null,
    sender_account_number    varchar(20)  not null,
    money_ammount            numeric      not null,
    currency_code            varchar(3)   not null,
    recipient_account_number varchar(20)  not null,
    recipient_name           varchar(255) not null,
    recipient_surname        varchar(255) not null,
    recipient_bank           bigint       not null
        constraint recipient_bank_fk
            references ibank_schema.bank_data
            on delete restrict,
    payment_purpose          varchar(255),
    transaction_fee          numeric      not null,
    transaction_time         timestamp
);

comment on table ibank_schema.outcoming_transaction is 'Исходящая транзакция';

comment on column ibank_schema.outcoming_transaction.sender_name is 'Имя отправителя';

comment on column ibank_schema.outcoming_transaction.sender_surname is 'Фамилия отправителя';

comment on column ibank_schema.outcoming_transaction.sender_account_number is 'Номер аккаунта отправителя';

comment on column ibank_schema.outcoming_transaction.money_ammount is 'Сумма';

comment on column ibank_schema.outcoming_transaction.currency_code is 'Валюта';

comment on column ibank_schema.outcoming_transaction.recipient_account_number is 'Аккаунт получателя';

comment on column ibank_schema.outcoming_transaction.recipient_name is 'Имя получателя';

comment on column ibank_schema.outcoming_transaction.recipient_surname is 'Фамилия получателя';

comment on column ibank_schema.outcoming_transaction.recipient_bank is 'Банк-получатель';

comment on column ibank_schema.outcoming_transaction.payment_purpose is 'Назначение платежа';

comment on column ibank_schema.outcoming_transaction.transaction_fee is 'Комиссия';

comment on column ibank_schema.outcoming_transaction.transaction_time is 'Timestamp транзакции';

alter table ibank_schema.outcoming_transaction
    owner to postgres;

create table if not exists ibank_schema.credit_offer
(
    id                 bigint generated always as identity
        constraint credit_offer_pk
            primary key,
    credit_name        varchar(50)      not null
        constraint credit_offer_pk2
            unique,
    credit_valid_from  timestamp        not null,
    credit_valid_till  timestamp,
    credit_interest    double precision not null,
    credit_fine        double precision not null,
    credit_min_term    smallint,
    credit_max_term    smallint,
    credit_min_ammount numeric,
    credit_max_ammount numeric,
    credit_remarks     varchar(255),
    credit_currency    bigint           not null
        constraint credit_currency_fk
            references ibank_schema.currency_data
            on update restrict on delete restrict
);

comment on table ibank_schema.credit_offer is 'Каталог кредитных предложений';

comment on column ibank_schema.credit_offer.credit_name is 'Название кредитного предложения (КП)';

comment on column ibank_schema.credit_offer.credit_valid_from is 'Начало действия КП';

comment on column ibank_schema.credit_offer.credit_valid_till is 'Конец действия КП';

comment on column ibank_schema.credit_offer.credit_interest is 'Годовая процентная ставка КП';

comment on column ibank_schema.credit_offer.credit_fine is 'Пеня за просрочку по кредиту, % годовых';

comment on column ibank_schema.credit_offer.credit_min_term is 'Минимальный срок КП, дней';

comment on column ibank_schema.credit_offer.credit_max_term is 'Максимальный срок КП, дней';

comment on column ibank_schema.credit_offer.credit_min_ammount is 'Минимальная сумма кредитования';

comment on column ibank_schema.credit_offer.credit_max_ammount is 'Максимальная сумма кредитования';

comment on column ibank_schema.credit_offer.credit_currency is 'Валюта кредитования';

alter table ibank_schema.credit_offer
    owner to postgres;

create table if not exists ibank_schema.credit_req_details
(
    id                     bigint generated always as identity
        constraint credit_req_details_pk
            primary key,
    credit_req_name        varchar(30) not null,
    credit_req_description varchar(255)
);

comment on table ibank_schema.credit_req_details is 'Каталог возможных кредитных требований';

comment on column ibank_schema.credit_req_details.credit_req_name is 'Заголовок требования';

comment on column ibank_schema.credit_req_details.credit_req_description is 'Тело требования';

alter table ibank_schema.credit_req_details
    owner to postgres;

create table if not exists ibank_schema.credit_req
(
    id                    bigint generated always as identity
        constraint credit_req_pk
            primary key,
    credit_offer_id       bigint not null
        constraint credit_offer_fk
            references ibank_schema.credit_offer,
    credit_req_details_id bigint not null
        constraint credit_req_details_fk
            references ibank_schema.credit_req_details
);

comment on table ibank_schema.credit_req is 'Требования кредитных предложений';

comment on column ibank_schema.credit_req.credit_offer_id is 'ID Кредитного предложения';

comment on column ibank_schema.credit_req.credit_req_details_id is 'ID требования';

alter table ibank_schema.credit_req
    owner to postgres;

create table if not exists ibank_schema.credit_issued
(
    id                     bigint generated always as identity
        constraint credit_issued_pk
            primary key,
    client_id              bigint    not null
        constraint client_id_fk
            references ibank_schema.client_data,
    manager_id             bigint    not null
        constraint manager_id_fk
            references ibank_schema.manager_data,
    credit_body            numeric   not null,
    credit_issued_date     timestamp,
    credit_next_payment    timestamp,
    credit_fine            numeric,
    credit_exp_date        timestamp not null,
    credit_interest        numeric   not null,
    credit_monthly_payment numeric,
    credit_overdue         numeric,
    credit_left            numeric   not null,
    credit_offer_id        bigint    not null
        constraint credit_offer_id_fk
            references ibank_schema.credit_offer
);

comment on table ibank_schema.credit_issued is 'Выданные кредиты';

comment on column ibank_schema.credit_issued.client_id is 'ID клиента';

comment on column ibank_schema.credit_issued.manager_id is 'Менеджер, одобривший кредит';

comment on column ibank_schema.credit_issued.credit_body is 'Сумма кредитного займа';

comment on column ibank_schema.credit_issued.credit_issued_date is 'Trigger, on create';

comment on column ibank_schema.credit_issued.credit_next_payment is 'Trigger, on create';

comment on column ibank_schema.credit_issued.credit_fine is 'При создании 0. Будет начислятся триггером при просрочке платежа';

comment on column ibank_schema.credit_issued.credit_exp_date is 'Планируемый срок погашения кредита';

comment on column ibank_schema.credit_issued.credit_interest is 'Интерес банка (рассчитывается сразу на весь срок займа)';

comment on column ibank_schema.credit_issued.credit_monthly_payment is 'Сумма ежемесячного платежа';

comment on column ibank_schema.credit_issued.credit_overdue is 'Просрочка по кредиту. Служебное поле';

comment on column ibank_schema.credit_issued.credit_left is 'Остаток по кредиту';

comment on column ibank_schema.credit_issued.credit_offer_id is 'Ссылка на КП';

alter table ibank_schema.credit_issued
    owner to postgres;

create table if not exists ibank_schema.credit_payments
(
    id               bigint generated always as identity
        constraint credit_payments_pk
            primary key,
    client_id        bigint  not null
        constraint client_id_fk
            references ibank_schema.client_data,
    credit_issued_id bigint  not null
        constraint credit_issued_id_fk
            references ibank_schema.credit_issued,
    payment_date     timestamp,
    payment_ammount  numeric not null
);

comment on table ibank_schema.credit_payments is 'Cовершенные платежи по кредиту';

comment on column ibank_schema.credit_payments.client_id is 'ID клиента';

comment on column ibank_schema.credit_payments.credit_issued_id is 'ID выданного кредита';

comment on column ibank_schema.credit_payments.payment_date is 'Дата платежа';

comment on column ibank_schema.credit_payments.payment_ammount is 'Сумма платежа';

alter table ibank_schema.credit_payments
    owner to postgres;

create table if not exists ibank_schema.block_reason
(
    id                bigint generated always as identity
        constraint block_reason_pk
            primary key,
    block_reason      varchar(30) not null,
    block_description varchar(255)
);

comment on table ibank_schema.block_reason is 'Каталог причин блокировок';

comment on column ibank_schema.block_reason.block_reason is 'Причина блокировки';

comment on column ibank_schema.block_reason.block_description is 'Описание блокировки';

alter table ibank_schema.block_reason
    owner to postgres;

create table if not exists ibank_schema.checking_account_status
(
    id                   bigint generated always as identity
        constraint account_status_pk
            primary key,
    manager_id           bigint    not null
        constraint manager_id_fk
            references ibank_schema.manager_data,
    block_reason_id      bigint    not null
        constraint block_reason_fk
            references ibank_schema.block_reason
            on update restrict on delete restrict,
    account_block_date   timestamp not null,
    account_unblock_date timestamp,
    checking_account_id  bigint    not null
        constraint checking_account_id_fk
            references ibank_schema.checking_account_data
);

comment on table ibank_schema.checking_account_status is 'Текущий счет - статус (если есть открытый статус то счет заблокирован)';

comment on column ibank_schema.checking_account_status.manager_id is 'ID менеджера';

comment on column ibank_schema.checking_account_status.block_reason_id is 'Причина блокировки';

comment on column ibank_schema.checking_account_status.account_block_date is 'Дата блокировки счета';

comment on column ibank_schema.checking_account_status.account_unblock_date is 'Дата разблокировки счета';

comment on column ibank_schema.checking_account_status.checking_account_id is 'ID текущего счета';

alter table ibank_schema.checking_account_status
    owner to postgres;

create table if not exists ibank_schema.card_account_status
(
    id                bigint generated always as identity
        constraint card_status_pk
            primary key,
    manager_id        bigint    not null
        constraint card_manager_id_fk
            references ibank_schema.manager_data,
    block_reason_id   bigint    not null
        constraint block_reason_fk
            references ibank_schema.block_reason
            on update restrict on delete restrict,
    card_block_date   timestamp not null,
    card_unblock_date timestamp,
    card_account_id   bigint    not null
        constraint card_account_id_fk
            references ibank_schema.card_account_data
);

comment on table ibank_schema.card_account_status is 'Карточный счет - статус (если есть открытый статус то счет заблокирован)';

comment on column ibank_schema.card_account_status.manager_id is 'ID менеджера';

comment on column ibank_schema.card_account_status.block_reason_id is 'Причина блокировки';

comment on column ibank_schema.card_account_status.card_block_date is 'Дата блокировки';

comment on column ibank_schema.card_account_status.card_unblock_date is 'Дата разблокировки';

comment on column ibank_schema.card_account_status.card_account_id is 'ID карточного счета';

alter table ibank_schema.card_account_status
    owner to postgres;

create table if not exists ibank_schema.card_account_data_limit
(
    id                    bigint generated always as identity
        constraint card_account_data_limit_concept_pk
            primary key,
    card_account_data_id  bigint not null
        constraint card_account_data_limit_concept_pk2
            unique
        constraint card_account_data_id_fk
            references ibank_schema.card_account_data,
    monthly_limit_ammount numeric,
    monthly_limit_used    numeric,
    monthly_limit_month   date,
    daily_limit_ammount   numeric,
    daily_limit_used      numeric,
    daily_limit_date      date
);

comment on table ibank_schema.card_account_data_limit is 'Лимиты карточного счета';

comment on column ibank_schema.card_account_data_limit.monthly_limit_ammount is 'Порог месячного лимита (NULL = лимитов нет)';

comment on column ibank_schema.card_account_data_limit.monthly_limit_used is 'Сумма совершенных транзакций за мес';

comment on column ibank_schema.card_account_data_limit.monthly_limit_month is 'Расчетный месяц';

comment on column ibank_schema.card_account_data_limit.daily_limit_ammount is 'Порог дневного лимита (NULL = лимитов нет)';

comment on column ibank_schema.card_account_data_limit.daily_limit_used is 'Сумма совершенных транзакций за день';

comment on column ibank_schema.card_account_data_limit.daily_limit_date is 'Расчетный день';

alter table ibank_schema.card_account_data_limit
    owner to postgres;

