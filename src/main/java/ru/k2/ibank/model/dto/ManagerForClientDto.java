package ru.k2.ibank.model.dto;

import lombok.Value;

@Value
public class ManagerForClientDto {

    String managerName;
    String managerMiddlename;
    String managerSurname;
    String managerEmail;
    String managerPhone;

}