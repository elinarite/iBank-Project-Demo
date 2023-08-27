package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.dto.CardAccountDataLimitDto;
import ru.k2.ibank.model.entity.CardAccountDataLimit;
import ru.k2.ibank.model.search.CardAccountDataLimitSearchValue;
import ru.k2.ibank.service.CardAccountDataLimitService;
import ru.k2.ibank.util.exceptions.ErrorMessage;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("card-limit")
public class CardAccountDataLimitController {

    private final CardAccountDataLimitService cardAccountDataLimitService;

    public CardAccountDataLimitController(CardAccountDataLimitService cardAccountDataLimitService) {
        this.cardAccountDataLimitService = cardAccountDataLimitService;
    }

    @GetMapping("/{ownerId}/get") //todo
    public ResponseEntity<CardAccountDataLimitDto> findByOwnerForUser(
            @PathVariable Long ownerId) {
        return cardAccountDataLimitService.findByOwnerForUser(ownerId);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CardAccountDataLimit> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cardAccountDataLimitService.findById(id));
    }

    @GetMapping("/id")
    public ResponseEntity<CardAccountDataLimit> findById(@RequestBody Long id) {
        return ResponseEntity.ok(cardAccountDataLimitService.findById(id));
    }

    @GetMapping("/all/short")
    public ResponseEntity<List<CardAccountDataLimitDto>> findAllShort() {
        return ResponseEntity.ok(cardAccountDataLimitService.findAllShort());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<CardAccountDataLimit>> findAll() {
        return cardAccountDataLimitService.findAll();
    }

    @GetMapping("/accountId")
    public ResponseEntity<List<CardAccountDataLimit>> findByCardAccountDataId(@RequestBody Long accountId) {
        return cardAccountDataLimitService.findByCardAccountDataId(accountId);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CardAccountDataLimitDto>> searchByParam(@RequestBody CardAccountDataLimitSearchValue cardAccountDataLimitSearchValue) {
        List<CardAccountDataLimitDto> searchList = cardAccountDataLimitService.search(cardAccountDataLimitSearchValue);
        if (searchList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(searchList);
    }

    //todo
    @PutMapping("/{ownerId}/update-limits")
    public ResponseEntity<String> updateLimits(
            @PathVariable Long ownerId,
            @RequestParam(required = false) BigDecimal newDailyLimit,
            @RequestParam(required = false) BigDecimal newMonthlyLimit) {

        CardAccountDataLimit existingCardAccountLimit = cardAccountDataLimitService.findByOwner(ownerId);
        if (existingCardAccountLimit == null) {
            return new ResponseEntity<>(ErrorMessage.NOT_FOUND_RECORDS + ownerId, HttpStatus.NOT_FOUND);
        }
        cardAccountDataLimitService.updateLimits(ownerId, newDailyLimit, newMonthlyLimit);

        return ResponseEntity.ok(ErrorMessage.UPDATE);
    }


    //todo, @OnetoOne to CardAccountData
    @PostMapping("/add")
    public ResponseEntity<CardAccountDataLimit> add(@Valid @RequestBody CardAccountDataLimit cardAccountDataLimit) {

        return new ResponseEntity<>(cardAccountDataLimitService.add(cardAccountDataLimit), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CardAccountDataLimit> update(@Valid @RequestBody CardAccountDataLimit cardAccountDataLimit) {

        Long id = cardAccountDataLimit.getId();
        CardAccountDataLimit existingCardAccountLimit = cardAccountDataLimitService.findById(id);
        if (id == existingCardAccountLimit.getId()) {
            cardAccountDataLimitService.update(cardAccountDataLimit, id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        cardAccountDataLimitService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }
}