package ru.k2.ibank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditReqByIdDto {

    private Long id;
    private Long creditOfferId;
    private String creditName;
    private Long creditReqId;
    private String creditReqName;

}
