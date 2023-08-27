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
import ru.k2.ibank.model.entity.CountryData;
import ru.k2.ibank.service.CountryDataService;
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
@Tag(name = "Country Data", description = "Controller for manage Countries")
@RequestMapping("/country")
public class CountryDataController {

    private final CountryDataService countryDataService;

    @Operation(
            summary = "Get COUNTRY by Id",
            description = "Returning object with full details where requested parameter is ID of country")
    @GetMapping("/id")
    public ResponseEntity<CountryData> findById(@RequestBody Long id){
        return countryDataService.findById(id);
    }

    @Operation(
            summary = "Get all COUNTRY`s from DB",
            description = "Returning collection of objects COUNTRY with full details")
    @GetMapping("/all")
    public ResponseEntity<List<CountryData>> findAll(){
        return countryDataService.findAll();
    }

    @Operation(
            summary = "Get COUNTRY`s by code",
            description = "Returning collection of objects COUNTRY where requested parameter is country code")
    @GetMapping("/code")
    public ResponseEntity<List<CountryData>> findByCountryCode(@RequestBody String countryCode){
        return countryDataService.findByCountryCode(countryCode);
    }

    @Operation(
            summary = "Get COUNTRY`s by name",
            description = "Returning collection of objects COUNTRY where requested parameter is part of country name")
    @GetMapping("/name")
    public ResponseEntity<List<CountryData>> findByName(@RequestBody String name){
        return countryDataService.findByName(name);
    }

    @Operation(
            summary = "Add COUNTRY",
            description = "Sending object COUNTRY to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<CountryData> add (@RequestBody @Valid CountryData countryData){
        return new ResponseEntity<>(countryDataService.add(countryData), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update COUNTRY",
            description = "Sending object COUNTRY to DB for update. ID must be filled (by ID will be updated specific COUNTRY)")
    @PostMapping("/update")
    public ResponseEntity<CountryData> update(@RequestBody @Valid CountryData countryData){
        Long id = countryData.getId();
        countryDataService.update(countryData, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete COUNTRY",
            description = "Sending id of COUNTRY for delete object from DB")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id){
        countryDataService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
