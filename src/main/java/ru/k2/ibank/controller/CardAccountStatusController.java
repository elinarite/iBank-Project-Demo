package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.dto.CardAccountStatusDto;
import ru.k2.ibank.model.entity.CardAccountStatus;
import ru.k2.ibank.service.CardAccountDataService;
import ru.k2.ibank.service.CardAccountStatusService;
import ru.k2.ibank.util.exceptions.ErrorMessage;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/card-status")
public class CardAccountStatusController {
    private final CardAccountStatusService cardAccountStatusService;

    public CardAccountStatusController(CardAccountStatusService cardAccountStatusService) {
        this.cardAccountStatusService = cardAccountStatusService;
    }

    @GetMapping("/{ownerId}/get") //todo
    public ResponseEntity<CardAccountStatusDto> findByOwner(
            @PathVariable Long ownerId) {
        return cardAccountStatusService.findByOwnerForUser(ownerId);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CardAccountStatus> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cardAccountStatusService.findById(id));
    }

    @GetMapping("/id")
    public ResponseEntity<CardAccountStatus> findById(@RequestBody Long id) {
        return ResponseEntity.ok(cardAccountStatusService.findById(id));
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<CardAccountStatus>> findAll() {
        return cardAccountStatusService.findAll();
    }

    @GetMapping("/all/short")
    public ResponseEntity<List<CardAccountStatusDto>> findAllShort() {
        List<CardAccountStatusDto> cardStatusDtos = cardAccountStatusService.findAllShort();
        return ResponseEntity.ok(cardStatusDtos);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CardAccountStatus>> search(
            @RequestParam(required = false) Long managerId,
            @RequestParam(required = false) Long blockReasonId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateBlockFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateBlockTo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateUnblockFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateUnblockTo) {

        return cardAccountStatusService.searchByParam(managerId, blockReasonId,
                dateBlockFrom, dateBlockTo,
                dateUnblockFrom, dateUnblockTo);
    }

    @PutMapping("/{id}/update-unblock-day")
    public ResponseEntity<String> updateUnblockDay(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date cardUnblockDate) {

        CardAccountStatus existingCardAccountStatus = cardAccountStatusService.findById(id);
        if (existingCardAccountStatus == null) {
            return new ResponseEntity<>(ErrorMessage.NOT_FOUND_RECORDS + id, HttpStatus.NOT_FOUND);
        }
        cardAccountStatusService.updateUnblockDay(id, cardUnblockDate);

        return ResponseEntity.ok(ErrorMessage.UPDATE);
    }

    //todo, @OnetoOne to CardAccountData
    @PostMapping("/add")
    public ResponseEntity<CardAccountStatus> add(@Valid @RequestBody CardAccountStatus cardAccountStatus) {

        return new ResponseEntity<>(cardAccountStatusService.add(cardAccountStatus), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CardAccountStatus> update(@Valid @RequestBody CardAccountStatus cardAccountStatus) {

        Long id = cardAccountStatus.getId();
        CardAccountStatus existingBlockReason = cardAccountStatusService.findById(id);
        if (id == existingBlockReason.getId()) {
            cardAccountStatusService.update(cardAccountStatus, id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}