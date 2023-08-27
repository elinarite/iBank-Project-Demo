package ru.k2.ibank.model.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardAccountDataLimitSearchValue {

    String cardAccountNumber;
    BigDecimal monthlyLimitAmountFrom;
    BigDecimal monthlyLimitAmountTo;
    BigDecimal monthlyLimitUsedFrom;
    BigDecimal monthlyLimitUsedTo;
    BigDecimal dailyLimitAmountFrom;
    BigDecimal dailyLimitAmountTo;
    BigDecimal dailyLimitUsedFrom;
    BigDecimal dailyLimitUsedTo;
    Date dailyDateFrom;
    Date dailyDateTo;

    private Integer pageNumber = 0;
    private Integer pageSize = 10;

    private String sortColumn;
    private String sortDirection;
}