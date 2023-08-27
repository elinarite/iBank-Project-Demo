package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.dto.CardAccountDataDto;
import ru.k2.ibank.model.entity.CardAccountData;
import ru.k2.ibank.model.search.CardAccountDataSearchValue;
import ru.k2.ibank.service.CardAccountDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/card-account")
public class CardAccountDataController {
    private final CardAccountDataService cardAccountDataService;

    public CardAccountDataController(CardAccountDataService cardAccountDataService) {
        this.cardAccountDataService = cardAccountDataService;
    }

    //todo with auth
    @GetMapping("/{ownerId}/accounts")
    public ResponseEntity<List<CardAccountDataDto>> getAllByClient(@PathVariable Long ownerId) {
        List<CardAccountDataDto> cardAccountDataDtoList = cardAccountDataService.findAllByClientData(ownerId);
        return ResponseEntity.ok(cardAccountDataDtoList);
    }

    //todo with auth
    @GetMapping("/{ownerId}/account")
    public ResponseEntity<CardAccountDataDto> getByIdAndClient(@PathVariable Long ownerId, @RequestBody Long accountId) {
        CardAccountDataDto cardAccountDataDto = cardAccountDataService.findByIdAndClientId(ownerId, accountId);
        return ResponseEntity.ok(cardAccountDataDto);
    }

    //todo with auth
    @GetMapping("/{ownerId}/account-by-currency")
    public ResponseEntity<CardAccountDataDto> getByCurrencyAndClient(@PathVariable Long ownerId, @RequestBody Long currencyId) {
        CardAccountDataDto cardAccountDataDtoList = cardAccountDataService.findByCurrencyIdAndClientId(ownerId, currencyId);
        return ResponseEntity.ok(cardAccountDataDtoList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CardAccountData> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cardAccountDataService.findById(id));
    }

    @GetMapping("/id")
    public ResponseEntity<CardAccountData> findById(@RequestBody Long id) {
        return ResponseEntity.ok(cardAccountDataService.findById(id));
    }

    @GetMapping("/all/by-currency")
    public ResponseEntity<List<CardAccountDataDto>> findAlLByCurrencyId(@RequestBody Long currencyId) {
        return ResponseEntity.ok(cardAccountDataService.findAlLByCurrencyId(currencyId));
    }

    @GetMapping("/all/short")
    public ResponseEntity<List<CardAccountDataDto>> findAllShort() {
        return ResponseEntity.ok(cardAccountDataService.findAllShort());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<CardAccountData>> findAll() {

        return cardAccountDataService.findAll();
    }

    @GetMapping("/all/sorted-by-balance")
    public ResponseEntity<List<CardAccountDataDto>> findAllOrderedByBalanceAsc() {

        return ResponseEntity.ok(cardAccountDataService.findAllOrderedByBalanceAsc());
    }


    @PostMapping("/search")
    public ResponseEntity<Page<CardAccountData>> searchByParam(@RequestBody CardAccountDataSearchValue cardAccountDataSearchValue) {
        Page<CardAccountData> result = cardAccountDataService.search(cardAccountDataSearchValue);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<CardAccountData> add(@Valid @RequestBody CardAccountData cardAccountData) {
        return new ResponseEntity<>(cardAccountDataService.add(cardAccountData), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CardAccountData> update(@Valid @RequestBody CardAccountData cardAccountData) {

        Long id = cardAccountData.getId();
        CardAccountData existingCardAccount = cardAccountDataService.findById(id);
        if (id == existingCardAccount.getId()) {
            cardAccountDataService.update(cardAccountData, id);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}/update-is-blocked") //todo
    public ResponseEntity<String> updateIsBlockedByID(
            @PathVariable Long id,
            @RequestParam boolean isBlocked) {
        cardAccountDataService.updateIsBlockedByID(id, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/balance-list-by-client")
    public ResponseEntity<List<Object[]>> getTotalBalanceByClient() {
        return cardAccountDataService.getTotalBalanceByClient();
    }

    @GetMapping("/total-balance")
    public ResponseEntity<Map<String, Object>> getBalanceSummary() {
        return cardAccountDataService.getBalanceSummary();
    }

    @GetMapping("/balance-by-currency")
    public ResponseEntity<List<Object[]>> getTotalBalanceByCurrencyData() {
        return cardAccountDataService.getTotalBalanceByCurrencyData();
    }

    @GetMapping("/balance-client-id")
    public ResponseEntity<Map<String, Object>> getBalanceSummaryByClientId(@RequestBody Long clientId) {
        return cardAccountDataService.getBalanceSummaryByClientId(clientId);
    }
}