package ru.k2.ibank.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.k2.ibank.model.dto.CardAccountDataDto;
import ru.k2.ibank.model.entity.CardAccountData;


@Mapper(componentModel = "spring")
public interface CardAccountDataMapper {
    @Mapping(source = "currencyData.currencyCode", target = "currencyCode")
    @Mapping(source = "clientData.clientName", target = "clientName")
    @Mapping(source = "clientData.clientSurname", target = "clientSurname")
    CardAccountDataDto cardAccountDataDto(CardAccountData cardAccountData);

}