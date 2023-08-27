package ru.k2.ibank.model.mapper;

import org.mapstruct.Mapper;
import ru.k2.ibank.model.dto.ManagerForClientDto;
import ru.k2.ibank.model.entity.ManagerData;


@Mapper(componentModel = "spring")


public interface ManagerMapper {

    ManagerForClientDto toManagerForClientDto(ManagerData managerData);

}
