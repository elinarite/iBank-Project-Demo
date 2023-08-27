package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.dto.ManagerForClientDto;
import ru.k2.ibank.model.entity.ClientData;
import ru.k2.ibank.model.entity.ManagerData;
import ru.k2.ibank.model.mapper.ManagerMapper;
import ru.k2.ibank.model.search.ManagerDataSearchValue;
import ru.k2.ibank.repo.ManagerDataRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerDataService {
    private final ManagerDataRepository managerDataRepository;
    private final ManagerMapper managerMapper;

    public ManagerDataService(ManagerDataRepository managerDataRepository, ManagerMapper managerMapper) {
        this.managerDataRepository = managerDataRepository;
        this.managerMapper = managerMapper;
    }

    @Cacheable(cacheNames = {"managerDataById"}, key = "#id")
    public ManagerData findById(Long id) {
        return managerDataRepository.findById(id).get();

    }

    @Cacheable(cacheNames = {"managerDataAll"})
    public List<ManagerForClientDto> findAllShort() {
        List<ManagerData> allManagers = managerDataRepository.findAllOrderByManagerSurnameAsc();
        return allManagers.stream()
                .map(managerMapper::toManagerForClientDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"managerDataAll"})
    public ResponseEntity<List<ManagerData>> findAll() {
        List<ManagerData> allManagers = managerDataRepository.findAllOrderByManagerSurnameAsc();
        if (allManagers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allManagers);
    }

    @Cacheable(cacheNames = {"clientDataByManager"})
    public ResponseEntity<List<ClientData>> findByClientManagerId(Long managerId) {
        List<ClientData> allClients = managerDataRepository.findByClientManagerIdOrderByClientSurnameAsc(managerId);
        if (allClients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allClients);
    }

    @Cacheable(cacheNames = {"clientDataByManager"})
    public ResponseEntity<List<Object[]>> findByManagerWithCountClients() {
        return ResponseEntity.ok(managerDataRepository.findManagerWithCountClients());
    }

    @Cacheable(cacheNames = {"managerDataActive"})
    public ResponseEntity<List<ManagerData>> findAllActiveManagers() {
        List<ManagerData> activeManagers = managerDataRepository.findByManagerDismissDateIsNullOrderByManagerSurnameAsc();

        if (activeManagers.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(activeManagers);
    }

    @Cacheable(cacheNames = {"managerDataInactive"})
    public ResponseEntity<List<ManagerData>> findAllInactiveManagers() {
        List<ManagerData> inactiveManagers = managerDataRepository.findByManagerDismissDateIsNotNullOrderByManagerSurnameAsc();

        if (inactiveManagers.isEmpty()) {
            new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(inactiveManagers);
    }

    //todo пожалуйста проверьте, мне кажется эти запросы излишни
    @Cacheable(cacheNames = {"managerDataSearch"})
    public ResponseEntity<List<ManagerData>> searchByName(ManagerDataSearchValue managerDataSearchValue) {
        String managerName = managerDataSearchValue.getManagerName();
        String managerSurname = managerDataSearchValue.getManagerSurname();
        String managerMiddleName = managerDataSearchValue.getManagerMiddlename();

        List<ManagerData> managerByParam = managerDataRepository.searchByName(
                managerName,
                managerSurname,
                managerMiddleName);

        if (managerByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        return ResponseEntity.ok(managerByParam);
    }

    //todo пожалуйста проверьте, мне кажется эти запросы излишни
    @Cacheable(cacheNames = {"managerDataSearch"})
    public ResponseEntity<List<ManagerData>> searchByNameAndPassport(ManagerDataSearchValue managerDataSearchValue) {
        String managerName = managerDataSearchValue.getManagerName();
        String managerSurname = managerDataSearchValue.getManagerSurname();
        String managerMiddlename = managerDataSearchValue.getManagerMiddlename();
        String managerPassport = managerDataSearchValue.getManagerPassport();
        String managerPassportDepartmentCode = managerDataSearchValue.getManagerPassportDepartmentCode();

        List<ManagerData> managersByParam = managerDataRepository.searchByNameAndPassport(
                managerName,
                managerSurname,
                managerMiddlename,
                managerPassport,
                managerPassportDepartmentCode);

        if (managersByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(managersByParam);
    }

    //todo
    @Cacheable(cacheNames = {"managerDataSearch"})
    public Page<ManagerData> search(ManagerDataSearchValue managerDataSearchValue) {
        String managerName = managerDataSearchValue.getManagerName();
        String managerSurname = managerDataSearchValue.getManagerSurname();
        String managerMiddlename = managerDataSearchValue.getManagerMiddlename();
        Date managerBirthday = managerDataSearchValue.getManagerBirthday();
        char managerGender = managerDataSearchValue.getManagerGender();
        String managerPassport = managerDataSearchValue.getManagerPassport();
        Date managerPassportIssDate = managerDataSearchValue.getManagerPassportIssDate();
        Date managerPassportExpDate = managerDataSearchValue.getManagerPassportExpDate();
        String managerPassportDepartmentCode = managerDataSearchValue.getManagerPassportDepartmentCode();
        Date managerHiringDate = managerDataSearchValue.getManagerHiringDate();
        Date managerDismissDate = managerDataSearchValue.getManagerDismissDate();
        String managerEmail = managerDataSearchValue.getManagerEmail();
        String managerAddress = managerDataSearchValue.getManagerAddress();
        String managerPhone = managerDataSearchValue.getManagerPhone();
        String sortDirection = managerDataSearchValue.getSortDirection();
        String sortColumn = managerDataSearchValue.getSortColumn();
        Integer pageSize = managerDataSearchValue.getPageSize();
        Integer pageNumber = managerDataSearchValue.getPageNumber();

        Sort.Direction direction = sortDirection == null ||
                sortDirection.trim().length() == 0 ||
                sortDirection.equals("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn, "managerSurname");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return managerDataRepository.search(
                managerName,
                managerSurname,
                managerMiddlename,
                managerBirthday,
                managerGender,
                managerPassport,
                managerPassportIssDate,
                managerPassportExpDate,
                managerPassportDepartmentCode,
                managerHiringDate,
                managerDismissDate,
                managerEmail,
                managerAddress,
                managerPhone,
                pageRequest);
    }
}