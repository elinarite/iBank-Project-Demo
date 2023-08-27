package ru.k2.ibank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditOfferDto {

    private Long id;
    private String creditName;
    private Timestamp creditValidFrom;
    private Timestamp creditValidTill;
    private String creditInterest;
    private String creditFine;
    private String creditMinTerm;
    private String creditMaxTerm;
    private String creditMinAmount;
    private String creditMaxAmount;
    private String creditRemarks;
    private String currencyData;
    private List<CreditReqDetailDto> creditReqs;

}
