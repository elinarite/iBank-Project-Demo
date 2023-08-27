package ru.k2.ibank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
//
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CardAccountStatusDto {

    Long id;
    Long managerId;
    String managerSurname;
    String managerName;
    String clientSurname;
    String clientName;
    Long blockReasonId;
    String blockReason;
    Timestamp cardBlockDate;
    Timestamp cardUnblockDate;
    String cardAccountNumber;
}
