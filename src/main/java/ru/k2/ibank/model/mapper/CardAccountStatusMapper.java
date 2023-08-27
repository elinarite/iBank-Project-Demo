package ru.k2.ibank.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.k2.ibank.model.dto.CardAccountStatusDto;
import ru.k2.ibank.model.entity.CardAccountStatus;

@Mapper(componentModel = "spring")
public interface CardAccountStatusMapper {

    @Mapping(source = "managerData.managerSurname", target = "managerSurname")
    @Mapping(source = "managerData.managerName", target = "managerName")
    @Mapping(source = "blockReason.blockReason", target = "blockReason")
    @Mapping(source = "cardAccountData.cardAccountNumber", target = "cardAccountNumber")
    @Mapping(source = "cardAccountData.clientData.clientName", target = "clientName")
    @Mapping(source = "cardAccountData.clientData.clientSurname", target = "clientSurname")
    CardAccountStatusDto cardAccountStatusDto(CardAccountStatus cardAccountDataStatus);

}