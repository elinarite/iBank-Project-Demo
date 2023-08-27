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
import ru.k2.ibank.model.entity.ExchangeTicker;
import ru.k2.ibank.service.ExchangeTickerService;
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
@Tag(name = "Exchange ticker data", description = "Controller for manage Exchange ticker")
@RequestMapping("/exchangeTickers")
public class ExchangeTickerController {

    private final ExchangeTickerService service;

    @Operation(
            summary = "Get EXCHANGE TICKER by Id",
            description = "Returning object with full details where requested parameter is ID of exchange ticker")
    @GetMapping("/id")
    public ResponseEntity<ExchangeTicker> findById(@RequestBody Long id){
        return service.findById(id);
    }

    @Operation(
            summary = "Get all EXCHANGE TICKER`s from DB",
            description = "Returning collection of objects EXCHANGE TICKER with full details")
    @GetMapping("/all")
    public ResponseEntity<List<ExchangeTicker>> findAll(){
        return service.findAll();
    }

    @Operation(
            summary = "Get EXCHANGE TICKER`s by currency code or name",
            description = "Returning collection of objects EXCHANGE TICKER where requested parameter is part of currency name or ticker")
    @GetMapping("/search")
    public ResponseEntity<List<ExchangeTicker>> findByCurrency(@RequestBody String currency){
        return service.findByCurrency(currency);
    }

    @Operation(
            summary = "Add EXCHANGE TICKER",
            description = "Sending object EXCHANGE TICKER to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<ExchangeTicker> add (@RequestBody @Valid ExchangeTicker exchangeTicker){
        return new ResponseEntity<>(service.add(exchangeTicker), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update EXCHANGE TICKER",
            description = "Sending object EXCHANGE TICKER to DB for update. ID must be filled (by ID will be updated specific EXCHANGE TICKER)")
    @PostMapping("/update")
    public ResponseEntity<ExchangeTicker> update(@RequestBody @Valid ExchangeTicker exchangeTicker){
        Long id = exchangeTicker.getId();
        service.update(exchangeTicker, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete EXCHANGE TICKER",
            description = "Sending id of EXCHANGE TICKER for delete object from DB")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id){
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
