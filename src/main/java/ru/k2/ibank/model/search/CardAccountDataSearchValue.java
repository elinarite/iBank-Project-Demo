package ru.k2.ibank.model.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.k2.ibank.model.entity.CurrencyData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardAccountDataSearchValue {

    private String cardNumber;
    private String cardAccountNumber;
    private Timestamp issueDateFrom;
    private Timestamp issueDateTo;
    private Timestamp expDateFrom;
    private Timestamp expDateTo;
    private BigDecimal balanceFrom;
    private BigDecimal balanceTo;
    private boolean isBlocked;
    private String clientDataName;
    private String clientDataSurname;

    private Integer pageNumber = 0;
    private Integer pageSize = 10;

    private String sortColumn = "id"; // по умолчанию
    private String sortDirection;
}