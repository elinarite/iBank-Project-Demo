package ru.k2.ibank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditReqDetailDto {

    private String creditReqName;
    private String creditReqDescription;

}
