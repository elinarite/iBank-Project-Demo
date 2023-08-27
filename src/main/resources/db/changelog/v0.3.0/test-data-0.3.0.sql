--liquibase formatted sql
--changeset Anton:v0.3.0-test-data

INSERT INTO currency_data (currency_code, currency_name) VALUES ('RUB', 'Российский рубль ');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('USD', 'Доллар США');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('EUR', 'Евро');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('CNY', 'Китайский юань');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('GBP', 'Фунт стерлингов');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('KZT', 'Казахстанский тенге');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('BYN', 'Белорусский рубль');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('CHF', 'Швейцарский франк');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('TRY', 'Турецкая лира');
INSERT INTO currency_data (currency_code, currency_name) VALUES ('HKD', 'Гонконгский доллар');

INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Россия', 'RUS', 1);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('США', 'USA', 2);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Нидерланды', 'NLD', 3);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Германия', 'DEU', 3);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Франция', 'FRA', 3);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Китай', 'CHN', 6);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Великобритания', 'GBR', 7);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Казахстан', 'KAZ', 8);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Беларусь', 'BLR', 9);
INSERT INTO country_data (country_name, country_code, country_currency) VALUES ('Швейцария', 'CHE', 10);

INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank2', 'Bank2Address', 2, '234567890123456789012341', '22345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank3', 'Bank3Address', 3, '345678901234567890123456', '32345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank1', 'Bank1Address', 1, '123456789012345678901234', '12345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank4', 'Bank4Address', 4, '423456789012345678901234', '42345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank5', 'Bank5Address', 5, '523456789012345678901234', '52345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank6', 'Bank6Address', 6, '623456789012345678901234', '62345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank7', 'Bank7Address', 7, '723456789012345678901234', '72345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank8', 'Bank8Address', 8, '823456789012345678901234', '82345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank9', 'Bank9Address', 9, '923456789012345678901234', '92345678901');
INSERT INTO bank_data (bank_name, bank_address, bank_country, bank_iban, bank_swift) VALUES ('Bank10', 'Bank10Address', 10, '103456789012345678901234', '10345678901');

INSERT INTO block_reason (block_reason, block_description) VALUES ('Потеря карты', 'Карта была потеряна, клиент запросил блокировку счета');
INSERT INTO block_reason (block_reason, block_description) VALUES ('Мошенничество', 'Обнаружены подозрительные операции на счете, счет был заблокирован для защиты клиента');
INSERT INTO block_reason (block_reason, block_description) VALUES ('Недостаток средств', 'Счет был заблокирован из-за недостатка средств на счете');
INSERT INTO block_reason (block_reason, block_description) VALUES ('Неизвестная причина', 'Причина блокировки неизвестна, клиент обратился в банк для выяснения');
INSERT INTO block_reason (block_reason, block_description) VALUES ('Финансовые мошенничества', 'Обнаружены финансовые мошенничества, счет был заблокирован по решению службы безопасности банка');

INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('RUBUSD', 1, 2);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('USDRUB', 2, 1);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('RUBEUR', 1, 3);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('EURRUB', 3, 1);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('USDEUR', 2, 3);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('EURUSD', 3, 2);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('CNYUSD', 6, 2);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('GBPUSD', 7, 2);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('KZTUSD', 8, 2);
INSERT INTO exchange_ticker (ticker_name, currency_from, currency_to) VALUES ('BYNUSD', 9, 2);

INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('10111111111111111111', 'NameRecipient01', 'SurnameRecipient01', 'NameSender01', 'SurnameSender01', '11111111111', '11111111111111111111', 120.1 , 'USD', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('11111111111111111111', 'NameRecipient02', 'SurnameRecipient02', 'NameSender02', 'SurnameSender02', '11111111111', '11111111111111111111', 217.7 , 'USD', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('12111111111111111111', 'NameRecipient03', 'SurnameRecipient03', 'NameSender03', 'SurnameSender03', '11111111111', '11111111111111111111', 314.13, 'USD', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('13111111111111111111', 'NameRecipient04', 'SurnameRecipient04', 'NameSender04', 'SurnameSender04', '11111111111', '11111111111111111111', 411.19, 'RUB', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('14111111111111111111', 'NameRecipient05', 'SurnameRecipient05', 'NameSender05', 'SurnameSender05', '11111111111', '11111111111111111111', 508.25, 'RUB', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('15111111111111111111', 'NameRecipient06', 'SurnameRecipient06', 'NameSender06', 'SurnameSender06', '11111111111', '11111111111111111111', 605.31, 'RUB', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('16111111111111111111', 'NameRecipient07', 'SurnameRecipient07', 'NameSender07', 'SurnameSender07', '11111111111', '11111111111111111111', 702.37, 'RUB', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('17111111111111111111', 'NameRecipient08', 'SurnameRecipient08', 'NameSender08', 'SurnameSender08', '11111111111', '11111111111111111111', 799.43, 'EUR', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('18111111111111111111', 'NameRecipient09', 'SurnameRecipient09', 'NameSender09', 'SurnameSender09', '11111111111', '11111111111111111111', 896.49, 'CNY', 'message', '2023-07-26 14:17:26.000000');
INSERT INTO incoming_transaction (recipient_account_number, recipient_name, recipient_surname, sender_name, sender_surname, sender_bank_swift, sender_account_number, money_amount, currency_code, payment_purpose, transaction_time) VALUES ('19111111111111111111', 'NameRecipient10', 'SurnameRecipient10', 'NameSender10', 'SurnameSender10', '11111111111', '11111111111111111111', 993.55, 'USD', 'message', '2023-07-26 14:17:26.000000');

INSERT INTO manager_data (manager_name, manager_middlename, manager_surname, manager_birthday, manager_gender, manager_passport, manager_passport_iss_date, manager_passport_exp_date, manager_country_id, manager_phone, manager_email, manager_hiring_date, manager_dismiss_date, manager_passport_department_code, manager_adress) VALUES ('Иван', 'Петрович', 'Смирнов', '1990-05-15', 'М', 'AС123456', '2020-01-01', '2025-12-31', 1, '+79123456789', 'ivan_smirnov@mail.ru', '2015-07-01 10:00:00.000000', NULL, '1234567890', 'г. Москва, ул. Ленина, д. 1');
INSERT INTO manager_data (manager_name, manager_middlename, manager_surname, manager_birthday, manager_gender, manager_passport, manager_passport_iss_date, manager_passport_exp_date, manager_country_id, manager_phone, manager_email, manager_hiring_date, manager_dismiss_date, manager_passport_department_code, manager_adress) VALUES ('Мария', NULL, 'Козлова', '1985-11-25', 'Ж', 'CD654321', '2005-02-10', '2025-02-09', 2, '+79876543210', 'maria_kozlova@gmail.com', '2010-10-20 10:00:00.0000', NULL, '0987654321', 'г. Санкт-Петербург, пр. Невский, д. 10');
INSERT INTO manager_data (manager_name, manager_middlename, manager_surname, manager_birthday, manager_gender, manager_passport, manager_passport_iss_date, manager_passport_exp_date, manager_country_id, manager_phone, manager_email, manager_hiring_date, manager_dismiss_date, manager_passport_department_code, manager_adress) VALUES ('Алексей', 'Сергеевич', 'Иванов', '1982-08-10', 'М', 'EF987654', '2007-05-20', '2027-05-19', 1, '+79001234567', 'alex_ivanov@yandex.ru', '2005-06-15 10:00:00.000000', '2023-03-18 10:00:00.000000', '9876543210', 'г. Екатеринбург, ул. Гагарина, д. 5');
INSERT INTO manager_data (manager_name, manager_middlename, manager_surname, manager_birthday, manager_gender, manager_passport, manager_passport_iss_date, manager_passport_exp_date, manager_country_id, manager_phone, manager_email, manager_hiring_date, manager_dismiss_date, manager_passport_department_code, manager_adress) VALUES ('Елена', NULL, 'Попова', '1978-04-02', 'Ж', 'GH123987', '1998-09-05', '2028-09-04', 3, '+79998887766', 'elena_popova@mail.ru', '2000-03-25 10:00:00.000000', NULL, '3456789123', 'г. Нижний Новгород, ул. Октябрьская, д. 15');
INSERT INTO manager_data (manager_name, manager_middlename, manager_surname, manager_birthday, manager_gender, manager_passport, manager_passport_iss_date, manager_passport_exp_date, manager_country_id, manager_phone, manager_email, manager_hiring_date, manager_dismiss_date, manager_passport_department_code, manager_adress) VALUES ('Дмитрий', 'Васильевич', 'Михайлов', '1995-12-30', 'М', 'IJ789456', '2015-07-12', '2035-07-11', 4, '+79001112233', 'dmitry_mikhailov@gmail.com', '2020-11-05 10:00:00.000000', NULL, '1122334455', 'г. Казань, ул. Пушкина, д. 20');

INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender1', 'SurnameSender1', 11111111111111111111, 150.25, 'USD', '11111111111111111100', 'NameRecipient1', 'SurnameRecipient1', 1, 'message', 1.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender2', 'SurnameSender2', 21111111111111111111, 1233.54, 'RUB', '21111111111111111100', 'NameRecipient2', 'SurnameRecipient2', 2, 'message', 2.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender3', 'SurnameSender3', 31111111111111111111, 17850.25, 'EUR', '31111111111111111100', 'NameRecipient3', 'SurnameRecipient3', 3, 'message', 3.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender4', 'SurnameSender4', 41111111111111111111, 15120.25, 'CNY', '41111111111111111100', 'NameRecipient4', 'SurnameRecipient4', 4, 'message', 4.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender5', 'SurnameSender5', 51111111111111111111, 123450.25, 'GBP', '51111111111111111100', 'NameRecipient5', 'SurnameRecipient5', 5, 'message', 55.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender6', 'SurnameSender6', 61111111111111111111, 150.25, 'USD', '61111111111111111100', 'NameRecipient6', 'SurnameRecipient6', 6, 'message', 6.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender7', 'SurnameSender7', 71111111111111111111, 150.25, 'USD', '71111111111111111100', 'NameRecipient7', 'SurnameRecipient7', 7, 'message', 7.525456, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender8', 'SurnameSender8', 81111111111111111111, 150.25, 'USD', '81111111111111111100', 'NameRecipient8', 'SurnameRecipient8', 8, 'message', 8.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender9', 'SurnameSender9', 91111111111111111111, 150.25, 'USD', '91111111111111111100', 'NameRecipient9', 'SurnameRecipient9', 9, 'message', 9.525, '2023-07-26 13:16:03.000000');
INSERT INTO outcoming_transaction (sender_name, sender_surname, sender_account_number, money_ammount, currency_code, recipient_account_number, recipient_name, recipient_surname, recipient_bank, payment_purpose, transaction_fee, transaction_time) VALUES ('NameSender10', 'SurnameSender10', 10111111111111111111, 15433550.25, 'USD', '10111111111111111100', 'NameRecipient10', 'SurnameRecipient10', 1, 'message', 10.0, '2023-07-26 13:16:03.000000');

INSERT INTO client_data (client_name, client_surname, client_middlename, client_gender, client_birthdate, client_country_id, client_address, client_email, client_phone, client_manager_id, client_passport, client_passport_issue_date, client_passport_department_code, client_passport_exp_date) VALUES
                                                                                                                                                                                                                                                                                                            ('Иван', 'Смирнов', 'Александрович', 'M', '1990-05-15', 1, 'ул. Центральная, д. 123', 'ivan@example.com', '+79101234567', 1, 'AB123456', '2020-01-01', 'XYZ123', '2030-01-01'),
                                                                                                                                                                                                                                                                                                            ('Екатерина', 'Иванова', 'Петровна', 'F', '1985-09-20', 2, 'ул. Пушкина, д. 456', 'ekaterina@example.com', '+79203216548', 2, 'CD789012', '2019-03-10', 'ABC987', '2029-03-10'),
                                                                                                                                                                                                                                                                                                            ('Алексей', 'Козлов', 'Дмитриевич', 'M', '1988-12-01', 3, 'ул. Гагарина, д. 789', 'alexey@example.com', '+79098765432', 3, 'EF345678', '2021-06-20', 'LMN456', '2031-06-20'),
                                                                                                                                                                                                                                                                                                            ('Ольга', 'Новикова', 'Сергеевна', 'F', '1992-08-08', 4, 'ул. Ленина, д. 1001', 'olga@example.com', '+79102345678', 4, 'GH123456', '2018-02-14', 'OPQ789', '2028-02-14'),
                                                                                                                                                                                                                                                                                                            ('Дмитрий', 'Морозов', 'Анатольевич', 'M', '1995-11-30', 5, 'ул. Советская, д. 2002', 'dmitry@example.com', '+79205647381', 5, 'IJ456789', '2021-04-18', 'RST012', '2031-04-18'),
                                                                                                                                                                                                                                                                                                            ('Мария', 'Петрова', 'Игоревна', 'F', '1993-07-25', 6, 'ул. Октябрьская, д. 345', 'maria@example.com', '+79103129485', 3, 'KL789012', '2020-09-12', 'UVW345', '2030-09-12'),
                                                                                                                                                                                                                                                                                                            ('Андрей', 'Волков', 'Владимирович', 'M', '1987-04-03', 7, 'ул. Революции, д. 567', 'andrey@example.com', '+79204710293', 2, 'XY123456', '2019-11-05', 'BCD678', '2029-11-05'),
                                                                                                                                                                                                                                                                                                            ('Елена', 'Соколова', 'Сергеевна', 'F', '1991-11-12', 8, 'ул. Мира, д. 890', 'elena@example.com', '+79101029473', 4, 'EF234567', '2018-07-20', 'FGH901', '2028-07-20'),
                                                                                                                                                                                                                                                                                                            ('Сергей', 'Павлов', 'Иванович', 'M', '1984-12-31', 9, 'ул. Зеленая, д. 123', 'sergey@example.com', '+79205678102', 5, 'IJK345678', '2021-03-08', 'LMN234', '2031-03-08'),
                                                                                                                                                                                                                                                                                                            ('Татьяна', 'Федорова', 'Александровна', 'F', '1994-06-10', 10, 'ул. Садовая, д. 456', 'tatiana@example.com', '+79106547389', 3, 'OPQ456789', '2020-05-15', 'RST567', '2030-05-15'),
                                                                                                                                                                                                                                                                                                            ('Максим', 'Морозов', 'Витальевич', 'M', '1986-09-19', 1, 'ул. Центральная, д. 567', 'maxim@example.com', '+79203648571', 2, 'UVW567890', '2019-02-28', 'XYZ012', '2029-02-28'),
                                                                                                                                                                                                                                                                                                            ('Виктория', 'Егорова', 'Васильевна', 'F', '1992-01-02', 2, 'ул. Пушкина, д. 789', 'victoria@example.com', '+79102039586', 2, 'YZA123456', '2018-04-11', 'BCD789', '2028-04-11'),
                                                                                                                                                                                                                                                                                                            ('Артем', 'Николаев', 'Игоревич', 'M', '1988-11-09', 3, 'ул. Гагарина, д. 1001', 'artem@example.com', '+79207469385', 1, 'EFG234567', '2021-01-14', 'HIJ012', '2031-01-14'),
                                                                                                                                                                                                                                                                                                            ('Елена', 'Дмитриева', 'Сергеевна', 'F', '1996-05-20', 4, 'ул. Ленина, д. 234', 'elena@example.com', '+79105683749', 4, 'KLM345678', '2020-10-25', 'NOP234', '2030-10-25'),
                                                                                                                                                                                                                                                                                                            ('Илья', 'Кузнецов', 'Андреевич', 'M', '1987-07-18', 5, 'ул. Советская, д. 567', 'ilya@example.com', '+79204109283', 5, 'QRS567890', '2019-07-03', 'TUV901', '2029-07-03'),
                                                                                                                                                                                                                                                                                                            ('Александр', 'Соколов', 'Иванович', 'M', '1989-03-12', 6, 'ул. Октябрьская, д. 123', 'alexander@example.com', '+79107654893', 1, 'ABC123456', '2020-05-22', 'XYZ567', '2030-05-22'),
                                                                                                                                                                                                                                                                                                            ('Анна', 'Павлова', 'Васильевна', 'F', '1993-10-07', 7, 'ул. Революции, д. 456', 'anna@example.com', '+79209837461', 2, 'DEF234567', '2019-09-14', 'ABC789', '2029-09-14'),
                                                                                                                                                                                                                                                                                                            ('Павел', 'Егоров', 'Дмитриевич', 'M', '1985-06-28', 8, 'ул. Мира, д. 789', 'pavel@example.com', '+79105648723', 3, 'GHI345678', '2021-07-30', 'LMN901', '2031-07-30'),
                                                                                                                                                                                                                                                                                                            ('Юлия', 'Николаева', 'Сергеевна', 'F', '1990-02-15', 9, 'ул. Зеленая, д. 1001', 'yulia@example.com', '+79206574823', 4, 'JKL456789', '2018-12-08', 'OPQ234', '2028-12-08'),
                                                                                                                                                                                                                                                                                                            ('Глеб', 'Дмитриев', 'Александрович', 'M', '1994-09-05', 10, 'ул. Садовая, д. 234', 'gleb@example.com', '+79103284759', 5, 'MNO567890', '2020-03-17', 'RST901', '2030-03-17');

INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName01', '2023-07-26 00:00:00.000000', '2024-01-26 16:58:02.000000', 05.0, 04, 10, 0100, 1000, 100000, 'remark01', 01);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName02', '2023-07-26 00:00:00.000000', '2025-02-26 16:58:02.000000', 06.1, 05, 30, 0350, 1250, 110000, 'remark02', 02);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName03', '2023-07-26 00:00:00.000000', '2026-03-26 16:58:02.000000', 07.2, 06, 60, 0600, 1500, 120000, 'remark03', 03);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName04', '2023-07-26 00:00:00.000000', '2027-04-26 16:58:02.000000', 08.3, 07, 90, 0850, 1750, 130000, 'remark04', 04);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName05', '2023-07-26 00:00:00.000000', '2028-05-26 16:58:02.000000', 09.4, 08, 30, 1100, 2000, 140000, 'remark05', 05);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName06', '2023-07-26 00:00:00.000000', '2029-06-26 16:58:02.000000', 10.5, 09, 10, 1350, 2250, 150000, 'remark06', 06);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName07', '2023-07-26 00:00:00.000000', '2030-07-26 16:58:02.000000', 11.6, 10, 30, 1600, 2500, 160000, 'remark07', 07);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName08', '2023-07-26 00:00:00.000000', '2031-08-26 16:58:02.000000', 12.7, 11, 60, 1850, 2750, 170000, 'remark08', 08);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName09', '2023-07-26 00:00:00.000000', '2032-09-26 16:58:02.000000', 13.8, 12, 90, 2100, 3000, 180000, 'remark09', 09);
INSERT INTO credit_offer (credit_name, credit_valid_from, credit_valid_till, credit_interest, credit_fine, credit_min_term, credit_max_term, credit_min_ammount, credit_max_ammount, credit_remarks, credit_currency) VALUES ('CreditOfferName10', '2023-07-26 00:00:00.000000', '2033-10-26 16:58:02.000000', 14.9, 13, 30, 2350, 3250, 190000, 'remark10', 10);

INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName01', 'CreditReqBody01');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName02', 'CreditReqBody02');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName03', 'CreditReqBody03');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName04', 'CreditReqBody04');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName05', 'CreditReqBody05');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName06', 'CreditReqBody06');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName07', 'CreditReqBody07');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName08', 'CreditReqBody08');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName09', 'CreditReqBody09');
INSERT INTO credit_req_details (credit_req_name, credit_req_description) VALUES ('CreditReqName10', 'CreditReqBody10');

INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 01);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 02);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 03);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 04);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 05);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 06);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 07);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 08);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 09);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (1, 10);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (2, 4);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (2, 5);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (2, 6);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (2, 7);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (2, 8);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 05);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 06);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 07);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 08);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 09);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (3, 10);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 1);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 2);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 3);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 4);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 5);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (4, 6);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 3);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 4);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 5);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 6);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 7);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 8);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (5, 9);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 02);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 03);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 04);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 05);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 06);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 07);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 08);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 09);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (6, 10);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (7, 5);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (7, 6);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (7, 7);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (7, 8);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (8, 1);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (9, 2);
INSERT INTO credit_req (credit_offer_id, credit_req_details_id) VALUES (10, 3);

INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (1, 0.011055, '2023-07-14 09:51:54.346064');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (2, 90.257, '2023-07-14 09:52:31.259614');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (3, 0.009826, '2023-07-14 09:53:01.954961');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (4, 101.345, '2023-07-14 09:53:23.993379');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (5, 0.8903, '2023-07-14 09:54:12.296065');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (6, 1.12294, '2023-07-14 09:54:29.240823');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (7, 0.13952, '2023-07-14 12:39:41.910982');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (8, 1.3, '2023-07-14 12:39:41.910982');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (9, 0.00227, '2023-07-14 12:39:41.910982');
INSERT INTO exchange_rate (exch_ticker, rate, rate_date) VALUES (10, 0.33121, '2023-07-14 12:39:41.910982');

INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('1234567812345678', 110000000330303, '2023-07-05 12:00:00.00000', '2025-07-05 12:00:00.00000', 1, 1000, true, 1);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('8765432187654321', 110000000330321, '2023-07-06 12:00:00.00000', '2025-07-06 12:00:00.00000', 1, 500, true, 2);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('5555555555555555', 110000000330475, '2023-07-07 12:00:00.00000', '2025-07-07 12:00:00.00000', 2, 300, false, 3);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('4444444444444444', 110000000330567, '2023-07-08 12:00:00.00000', '2025-07-08 12:00:00.00000', 2, 200, true, 4);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('3333333333333333', 110000000730111, '2023-07-09 12:00:00.00000', '2025-07-09 12:00:00.00000', 3, 400, false, 5);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('2222222222222222', 110000000330302, '2023-07-10 12:00:00.00000', '2025-07-10 12:00:00.00000', 3, 600, false, 6);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('1111111111111111', 110000000330101, '2023-07-11 12:00:00.00000', '2025-07-11 12:00:00.00000', 1, 800, false, 7);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('6666666666666666', 110000000530303, '2023-07-12 12:00:00.00000', '2025-07-12 12:00:00.00000', 2, 700, false, 8);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('7777777777777777', 110000000340303, '2023-07-13 12:00:00.00000', '2025-07-13 12:00:00.00000', 3, 900, false, 9);
INSERT INTO card_account_data (card_number, card_account_number, card_issue_date, card_expiration_date, card_account_currency_id, card_account_balance, is_Blocked, account_client_id) VALUES ('8888888888888888', 110000007130303, '2023-07-14 12:00:00.00000', '2025-07-14 12:00:00.00000', 1, 1200, false, 10);

INSERT INTO card_account_status (manager_id, block_reason_id, card_block_date, card_unblock_date, card_account_id) VALUES (1, 2,  '2023-07-05 12:00:00.00000', NULL, 1);
INSERT INTO card_account_status (manager_id, block_reason_id, card_block_date, card_unblock_date, card_account_id) VALUES (2, 1, '2023-07-05 13:30:00.00000', NULL, 2);
INSERT INTO card_account_status (manager_id, block_reason_id, card_block_date, card_unblock_date, card_account_id) VALUES (3, 3, '2023-07-05 14:15:00.00000', '2023-07-06 10:00:00.00000', 3);
INSERT INTO card_account_status (manager_id, block_reason_id, card_block_date, card_unblock_date, card_account_id) VALUES (4, 5, '2023-07-05 16:45:00.00000', NULL, 4);
INSERT INTO card_account_status (manager_id, block_reason_id, card_block_date, card_unblock_date, card_account_id) VALUES (1, 4, '2023-07-05 17:30:00.00000', '2023-07-06 12:30:00.00000', 5);

INSERT INTO checking_account_data
(account_open_date, account_close_date, account_client_id, account_currency_id, account_balance, account_number, is_Blocked)
VALUES
    ('2000-04-12 08:30:00', null, 1, 2, 500.25, '2600123456789010', false),
    ('2001-03-15 12:30:00', '2022-09-05 18:45:00', 2, 1, 10000.00, '2600578234901283', true),
    ('2003-07-26 09:15:00', null, 3, 3, 250.50, '2600819347561032', true),
    ('2005-11-11 15:20:00', '2020-05-10 11:10:00', 4, 1, 5000.75, '2600982743650298', true),
    ('2008-09-30 05:55:00', null, 5, 3, 750.00, '2600118294567023', false),
    ('2010-12-05 14:40:00', null, 6, 2, 7000.20, '2600102034876520', true),
    ('2013-06-18 16:25:00', null, 7, 1, 320.10, '2600192837465019', false),
    ('2015-08-22 22:50:00', '2019-11-29 08:00:00', 8, 1, 1500.30, '2600865420378912', false),
    ('2017-04-01 11:35:00', null, 9, 3, 650.60, '2600789154362735', false),
    ('2020-02-14 19:05:00', null, 10, 1, 1800.90, '2600432569701284', false),
    ('2000-04-12 08:30:00', null, 11, 2, 1200.00, '2600759438210567', false),
    ('2002-09-25 17:45:00', '2022-11-08 13:20:00', 12, 2, 3000.50, '2600847269358102', false),
    ('2004-12-01 12:15:00', null, 13, 1, 800.75, '2600563248973510', true),
    ('2006-06-30 10:40:00', '2020-09-05 14:30:00', 14, 1, 4500.20, '2600384957029183', false),
    ('2009-03-17 19:55:00', null, 15, 1, 900.00, '2600981726354912', false),
    ('2011-07-22 23:20:00', null, 16, 2, 6200.30, '2600458192730645', true),
    ('2014-11-02 18:35:00', null, 17, 2, 280.10, '2600193847265901', false),
    ('2016-05-18 06:50:00', null, 18, 1, 1800.50, '2600847563920172', false),
    ('2018-08-03 14:25:00', null, 19, 3, 540.60, '2600956302718942', false),
    ('2021-03-12 09:05:00', '2023-05-20 15:55:00', 20, 7, 1500.90, '2600392817465903', false),
    ('2000-04-12 08:30:00', null, 2, 3, 1200.00, '2600358279619041', false),
    ('2002-09-25 17:45:00', '2022-11-08 13:20:00', 12, 8, 3000.50, '2600648203178613', false),
    ('2004-12-01 12:15:00', null, 8, 2, 800.75, '2600969032548134', true),
    ('2006-06-30 10:40:00', '2020-09-05 14:30:00', 1, 3, 4500.20, '2600578096312459', false),
    ('2009-03-17 19:55:00', null, 19, 3, 900.00, '2600935724819062', false),
    ('2011-07-22 23:20:00', '2021-08-15 10:10:00', 14, 2, 6200.30, '2600721468390537', true),
    ('2014-11-02 18:35:00', null, 17, 1, 280.10, '2600415932870645', false),
    ('2016-05-18 06:50:00', '2019-10-25 12:00:00', 7, 3, 1800.50, '2600803461251978', false),
    ('2018-08-03 14:25:00', null, 20, 3, 540.60, '2600489623159705', false),
    ('2021-03-12 09:05:00', null, 15, 1, 1500.90, '2600713489203562', false),
    ('2008-06-15 11:30:00', null, 5, 2, 320000.00, '2600913567204897', false),
    ('2010-11-25 14:00:00', null, 9, 1, 150000.00, '2600475128364905', false),
    ('2012-12-18 09:15:00', null, 11, 3, 80000.00, '2600602753918439', false),
    ('2015-08-05 13:40:00', null, 4, 2, 230000.00, '2600736891245371', false),
    ('2017-09-20 19:55:00', null, 13, 3, 380000.00, '2600597231489037', false),
    ('2019-12-10 10:20:00', null, 16, 1, 420000.00, '2600452983612047', false),
    ('2022-04-06 16:35:00', null, 18, 5, 290000.00, '2600128904359283', false),
    ('2024-01-15 08:00:00', null, 10, 3, 480000.00, '2600823749518650', false),
    ('2026-06-22 11:15:00', null, 3, 2, 380000.00, '2600712036951073', false),
    ('2028-09-29 14:30:00', null, 6, 3, 490000.00, '2600497136208527', false);

INSERT INTO checking_account_status (manager_id, block_reason_id, account_block_date, account_unblock_date, checking_account_id) VALUES
                                                                                                                                         (1, 1, '2022-11-08 13:20:00', '2022-11-09 10:30:00', 5),
                                                                                                                                         (2, 3, '2021-08-15 10:10:00', '2021-08-18 14:30:00', 12),
                                                                                                                                         (3, 2, '2019-10-25 12:00:00', '2019-10-26 15:00:00', 8),
                                                                                                                                         (2, 4, '2020-05-10 11:10:00', '2020-05-15 08:45:00', 31),
                                                                                                                                         (5, 1, '2019-11-29 08:00:00', '2019-11-30 10:00:00', 20),
                                                                                                                                         (2, 5, '2019-10-05 14:30:00', '2019-10-07 18:20:00', 18),
                                                                                                                                         (3, 3, '2019-09-18 16:25:00', '2019-09-20 11:30:00', 35),
                                                                                                                                         (4, 1, '2020-02-24 22:50:00', '2020-02-26 13:00:00', 25),
                                                                                                                                         (5, 2, '2019-05-01 11:35:00', '2019-05-03 09:45:00', 14),
                                                                                                                                         (1, 4, '2021-02-16 19:05:00', '2021-02-18 16:30:00', 30),
                                                                                                                                         (1, 3, '2023-05-20 15:55:00', '2023-05-22 14:15:00', 10),
                                                                                                                                         (2, 1, '2022-08-10 17:45:00', '2022-08-11 11:00:00', 7),
                                                                                                                                         (1, 2, '2023-01-12 18:35:00', '2023-01-15 09:00:00', 22),
                                                                                                                                         (2, 5, '2023-07-29 12:15:00', '2023-07-30 10:30:00', 40),
                                                                                                                                         (3, 3, '2022-12-20 10:40:00', '2022-12-22 14:00:00', 3),
                                                                                                                                         (1, 1, '2023-03-12 19:55:00', '2023-03-14 16:10:00', 37),
                                                                                                                                         (5, 2, '2023-04-10 23:20:00', '2023-04-13 12:45:00', 15),
                                                                                                                                         (2, 4, '2023-08-22 18:35:00', '2023-08-25 09:00:00', 23),
                                                                                                                                         (2, 1, '2023-06-02 06:50:00', '2023-06-04 14:00:00', 17),
                                                                                                                                         (1, 3, '2023-09-20 14:25:00', '2023-09-22 12:30:00', 26);

INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (3, 3, 60000, '2023-07-27 12:14:00.000000', '2023-08-27 12:14:00.000000', null, '2024-10-27 12:14:00.000000', 500, null, null, 60000, 3);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (5, 5, 70000, '2023-07-27 14:26:00.000000', '2023-08-27 14:26:00.000000', null, '2025-05-27 14:26:00.000000', 500, null, null, 70000, 5);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (6, 1, 75000, '2023-07-27 15:32:00.000000', '2023-08-27 15:32:00.000000', null, '2024-10-27 15:32:00.000000', 500, null, null, 75000, 6);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (7, 2, 80000, '2023-07-27 16:38:00.000000', '2023-08-27 16:38:00.000000', null, '2025-12-27 16:38:00.000000', 500, null, null, 80000, 7);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (8, 3, 85000, '2023-07-27 17:44:00.000000', '2023-08-27 17:44:00.000000', null, '2026-01-27 17:44:00.000000', 500, null, null, 85000, 8);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (9, 4, 90000, '2023-07-27 18:50:00.000000', '2023-08-27 18:50:00.000000', null, '2027-11-27 18:50:00.000000', 500, null, null, 90000, 9);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (4, 4, 65000, '2023-07-27 13:20:00.000000', '2023-08-27 13:20:00.000000', null, '2024-03-27 13:20:00.000000', 500, null, null, 57500, 4);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (10, 5, 95000, '2023-07-27 19:56:00.000000', '2023-08-27 19:56:00.000000', null, '2024-01-27 19:56:00.000000', 500, null, null, 93020, 10);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (1, 1, 50000, '2023-07-27 10:02:00.000000', '2023-08-27 10:02:00.000000', null, '2023-10-27 10:02:00.000000', 500, null, null, 42450, 1);
INSERT INTO credit_issued (client_id, manager_id, credit_body, credit_issued_date, credit_next_payment, credit_fine, credit_exp_date, credit_interest, credit_monthly_payment, credit_overdue, credit_left, credit_offer_id) VALUES (2, 2, 55000, '2023-07-27 11:08:00.000000', '2023-08-27 11:08:00.000000', null, '2023-12-27 11:08:00.000000', 500, null, null, 53750, 2);

INSERT INTO credit_payments (client_id, credit_issued_id, payment_date, payment_ammount) VALUES (1, 1, '2023-07-27 14:02:16.000000', 5000);
INSERT INTO credit_payments (client_id, credit_issued_id, payment_date, payment_ammount) VALUES (4, 4, '2023-07-28 10:00:00.000000', 7500);
INSERT INTO credit_payments (client_id, credit_issued_id, payment_date, payment_ammount) VALUES (2, 2, '2023-07-27 17:00:00.000000', 1250);
INSERT INTO credit_payments (client_id, credit_issued_id, payment_date, payment_ammount) VALUES (10, 10, '2023-07-28 09:00:00.000000', 1980);
INSERT INTO credit_payments (client_id, credit_issued_id, payment_date, payment_ammount) VALUES (3, 1, '2023-07-28 11:30:07.000000', 2550);

INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (1, 50000, 0, '2023-07-02 10:00:00.00000', 2000, 0, '2023-07-06 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (2, 70000, 30000, '2023-07-03 10:00:00.00000', 3000, 1500, '2023-07-05 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (3, 100000, 40000, '2023-07-01 10:00:00.00000', 4000, 2000, '2023-07-08 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (4, 60000, 25000, '2023-07-09 10:00:00.00000', 2500, 1200, '2023-07-05 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (5, 80000, 35000, '2023-07-16 10:00:00.00000', 3500, 1800, '2023-07-05 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (6, 90000, 40000, '2023-07-01 10:00:00.00000', 4000, 2000, '2023-07-05 10:00:00.00000');
INSERT INTO card_account_data_limit (card_account_data_id, monthly_limit_ammount, monthly_limit_used, monthly_limit_month, daily_limit_ammount, daily_limit_used, daily_limit_date) VALUES (7, 40000, 18000, '2023-07-01 10:00:00.00000', 1800, 800, '2023-07-05 10:00:00.00000');

