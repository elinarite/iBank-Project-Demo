package ru.k2.ibank.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.k2.ibank.model.dto.CardAccountDataLimitDto;
import ru.k2.ibank.model.entity.CardAccountDataLimit;


@Mapper(componentModel = "spring")
public interface CardAccountDataLimitMapper {
    @Mapping(source = "cardAccountData.cardAccountNumber", target = "cardAccountNumber")
    @Mapping(source = "cardAccountData.clientData.clientName", target = "clientName")
    @Mapping(source = "cardAccountData.clientData.clientSurname", target = "clientSurname")
    CardAccountDataLimitDto cardAccountDataLimitDto(CardAccountDataLimit cardAccountDataLimit);

}