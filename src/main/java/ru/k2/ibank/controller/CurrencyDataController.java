package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.entity.CurrencyData;
import ru.k2.ibank.service.CurrencyDataService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyDataController {

    private final CurrencyDataService currencyDataService;

    public CurrencyDataController(CurrencyDataService currencyDataService) {
        this.currencyDataService = currencyDataService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CurrencyData> getById(@PathVariable Long id) {
        return currencyDataService.findById(id);
    }

    @GetMapping("/id")
    public ResponseEntity<CurrencyData> findById(@RequestBody Long id) {
        return currencyDataService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyData>> findAll() {
        return currencyDataService.findAll();
    }


    @PostMapping("/search")
    public ResponseEntity<List<CurrencyData>> search(@RequestParam(required = false) String currencyName,
                                                     @RequestParam(required = false) String currencyCode) {
        return currencyDataService.findCurrencyDataByParam(currencyName, currencyCode);

    }

    @PostMapping("/add")
    public ResponseEntity<CurrencyData> add(@Valid @RequestBody CurrencyData currencyData) {
        return new ResponseEntity<>(currencyDataService.add(currencyData), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CurrencyData> update(@Valid @RequestBody CurrencyData currencyData) {
        Long id = currencyData.getId();
        ResponseEntity<CurrencyData> existingCurrency = currencyDataService.findById(id);
        if (id == existingCurrency.getBody().getId()) {
            currencyDataService.update(currencyData, id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}