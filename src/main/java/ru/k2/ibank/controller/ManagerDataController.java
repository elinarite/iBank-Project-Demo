package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.dto.ManagerForClientDto;
import ru.k2.ibank.model.entity.ClientData;
import ru.k2.ibank.model.entity.ManagerData;
import ru.k2.ibank.model.search.ManagerDataSearchValue;
import ru.k2.ibank.service.ManagerDataService;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerDataController {
    private final ManagerDataService managerDataService;

    public ManagerDataController(ManagerDataService managerDataService) {
        this.managerDataService = managerDataService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerData> getById(@PathVariable Long id) {
        return ResponseEntity.ok(managerDataService.findById(id));
    }

    @GetMapping("/id")
    public ResponseEntity<ManagerData> findById(@RequestBody Long id) {
        return ResponseEntity.ok(managerDataService.findById(id));
    }

    @GetMapping("/all/short")
    public ResponseEntity<List<ManagerForClientDto>> findAllShort() {
        return ResponseEntity.ok(managerDataService.findAllShort());

    }

    @GetMapping("/all/full")
    public ResponseEntity<List<ManagerData>> findAllFull() {
        return managerDataService.findAll();
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientData>> findByClientManagerId(@RequestBody Long managerId) {
        return managerDataService.findByClientManagerId(managerId);
    }

    /**
     * this method is used to display information about managers (manager id, name, Surname) and the number of clients
     */
    @GetMapping("/count-clients")
    public ResponseEntity<List<Object[]>> findByManagerWithCountClients() {
        return managerDataService.findByManagerWithCountClients();
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<ManagerData>> findAllActiveManagers() {
        return managerDataService.findAllActiveManagers();

    }

    @GetMapping("/all-inactive")
    public ResponseEntity<List<ManagerData>> findAllInactiveManagers() {
        return managerDataService.findAllInactiveManagers();
    }
    @PostMapping("/search/name")
    public ResponseEntity<List<ManagerData>> searchByName(@RequestBody ManagerDataSearchValue managerDataSearchValue) {
        return managerDataService.searchByName(managerDataSearchValue);
    }

    @PostMapping("/search/name-pass")
    public ResponseEntity<List<ManagerData>> searchByNameAndPassport(@RequestBody ManagerDataSearchValue managerDataSearchValue) {
        return managerDataService.searchByNameAndPassport(managerDataSearchValue);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ManagerData>> searchByParam(@RequestBody ManagerDataSearchValue managerDataSearchValue) {
        Page<ManagerData> result = managerDataService.search(managerDataSearchValue);
        return ResponseEntity.ok(result);
    }


}