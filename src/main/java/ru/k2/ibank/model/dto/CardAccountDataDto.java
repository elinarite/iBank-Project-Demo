package ru.k2.ibank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

    @RequiredArgsConstructor
    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)

    public class CardAccountDataDto {

        Long id;
        String cardNumber;
        String cardAccountNumber;
        String  currencyCode;
        BigDecimal cardAccountBalance;
        String clientName;
        String clientSurname;
        boolean blocked;

}
