package ru.k2.ibank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.entity.BankData;
import ru.k2.ibank.service.BankDataService;
import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad request - wrong input data",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionHandler.class)) }),
        @ApiResponse(responseCode = "404", description = "Not found",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionHandler.class)) }) })
@RestController
@RequiredArgsConstructor
@Tag(name = "Bank Data", description = "Controller for manage Banks")
@RequestMapping("/bank")
public class BankDataController {

    private final BankDataService bankDataService;

    @Operation(
            summary = "Get BANK by Id",
            description = "Returning object with full details where requested parameter is ID of bank")
    @GetMapping("/id")
    public ResponseEntity<BankData> findById(@RequestBody Long id){
        return bankDataService.findById(id);
    }

    @Operation(
            summary = "Get all BANKs from DB",
            description = "Returning collection of objects BANK with full details")
    @GetMapping("/all")
    public ResponseEntity<List<BankData>> findAll(){
        return bankDataService.findAll();
    }

    @Operation(
            summary = "Get BANKs by swift",
            description = "Returning collection of objects BANK where requested parameter is part of SWIFT code")
    @GetMapping("/swift")
    public ResponseEntity<List<BankData>> findBySwift(@RequestBody String swift){
        return bankDataService.findBySwift(swift);
    }

    @Operation(
            summary = "Get BANKs by name",
            description = "Returning collection of objects BANK where requested parameter is part of bank's name")
    @GetMapping("/name")
    public ResponseEntity<List<BankData>> findByName(@RequestBody String name){
        return bankDataService.findByName(name);
    }

    @Operation(
            summary = "Get BANKs by iban",
            description = "Returning collection of objects BANK where requested parameter is part of IBAN number")
    @GetMapping("/iban")
    public ResponseEntity<List<BankData>> findByIban(@RequestBody String iban){
        return bankDataService.findByIban(iban);
    }

    @Operation(
            summary = "Get BANKs by country of registration",
            description = "Returning collection of objects BANK where requested parameter is part of country name")
    @GetMapping("/country")
    public ResponseEntity<List<BankData>> findByCountry(@RequestBody String country){
        return bankDataService.findByCountry(country);
    }

    @Operation(
            summary = "Add BANK",
            description = "Sending object BANK to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<BankData> add (@RequestBody @Valid BankData bankData){
        return new ResponseEntity<>(bankDataService.add(bankData), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update BANK",
            description = "Sending object BANK to DB for save. ID must be filled (by ID will be updated specific BANK)")
    @PostMapping("/update")
    public ResponseEntity<BankData> update(@RequestBody @Valid BankData bankData){

        Long id = bankData.getId();

        bankDataService.update(bankData, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
