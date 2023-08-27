package ru.k2.ibank.model.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.k2.ibank.model.entity.CountryData;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDataSearchValue {

    private String managerName;
    private String managerSurname;
    private String managerMiddlename;
    private Date managerBirthday;
    private char managerGender;
    private CountryData countryDataId;
    private String managerPassport;
    private Date managerPassportIssDate;
    private Date managerPassportExpDate;
    private String managerPassportDepartmentCode;
    private Date managerHiringDate;
    private Date managerDismissDate;
    private String managerEmail;
    private String managerAddress;
    private String managerPhone;

    private Integer pageNumber = 0;
    private Integer pageSize = 10;

    private String sortColumn = "id";
    private String sortDirection;

}