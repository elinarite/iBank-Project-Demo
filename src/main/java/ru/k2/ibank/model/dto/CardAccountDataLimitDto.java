package ru.k2.ibank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CardAccountDataLimitDto {

    Long id;
    String cardAccountNumber;
    String clientName;
    String clientSurname;
    BigDecimal monthlyLimitAmount;
    BigDecimal monthlyLimitUsed;
    Date monthlyLimitMonth;
    BigDecimal dailyLimitAmount;
    BigDecimal dailyLimitUsed;
    Date dailyLimitDate;
}