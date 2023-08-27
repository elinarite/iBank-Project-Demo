package ru.k2.ibank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.dto.CreditOfferDto;
import ru.k2.ibank.model.entity.CreditOffer;
import ru.k2.ibank.model.search.CreditOfferSearchValues;
import ru.k2.ibank.service.CreditOfferService;
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
@Tag(name = "Credit offer", description = "Controller for manage credit offers")
@RequestMapping("/creditOffer")
public class CreditOfferController {

    private final CreditOfferService service;
    public CreditOfferController(CreditOfferService service){
        this.service = service;
    }

   @Operation(
            summary = "Get CREDIT OFFER by Id",
            description = "Returning object with full details where requested parameter is ID of credit offer")
    @GetMapping("/id")
    public ResponseEntity<CreditOfferDto> findById(@RequestBody Long id){
        return service.findById(id);
    }

    @Operation(
            summary = "Get all CREDIT OFFER`s from DB",
            description = "Returning collection of objects CREDIT OFFER with full details")
    @GetMapping("/all")
    public ResponseEntity<List<CreditOfferDto>> findAll(){
        return service.findAll();
    }

    @Operation(
            summary = "Get CREDIT OFFER`s by name",
            description = "Returning collection of objects CREDIT OFFER where requested parameter is country code")
    @GetMapping("/name")
    public ResponseEntity<List<CreditOfferDto>> findByName(@RequestBody String name){
        return service.findByName(name);
    }

    @Operation(
            summary = "Get valid CREDIT OFFER`s",
            description = "Returning collection of objects CREDIT OFFER which still valid for NOW")
    @GetMapping("/valid")
    public ResponseEntity<List<CreditOfferDto>> findAllValid(){
        return service.findAllValid();
    }

    @Operation(
            summary = "Get expired CREDIT OFFER`s",
            description = "Returning collection of objects CREDIT OFFER which expired")
    @GetMapping("/expired")
    public ResponseEntity<List<CreditOfferDto>> findAllExpired(){
        return service.findAllExpired();
    }

    @Operation(
            summary = "Get CREDIT OFFER`s by parameters",
            description = "Returning collection of objects CREDIT OFFER which still valid for NOW")
    @GetMapping("/params")
    public ResponseEntity<List<CreditOfferDto>> findByParams(@RequestBody CreditOfferSearchValues values){
        return service.findByParams(values);
    }

    @Operation(
            summary = "Add CREDIT OFFER",
            description = "Sending object CREDIT OFFER to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<CreditOffer> add (@RequestBody @Valid CreditOffer creditOffer){
        return new ResponseEntity<>(service.add(creditOffer), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update CREDIT OFFER",
            description = "Sending object CREDIT OFFER to DB for update. ID must be filled (by ID will be updated specific CREDIT OFFER)")
    @PostMapping("/update")
    public ResponseEntity<CreditOffer> update(@RequestBody @Valid CreditOffer creditOffer){
        Long id = creditOffer.getId();
        service.update(creditOffer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
