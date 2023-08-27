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
import ru.k2.ibank.model.entity.CreditReqDetails;
import ru.k2.ibank.service.CreditReqDetailsService;

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
@Tag(name = "Credit Requirement Details", description = "Controller for single credit requirements (not linking to Credit offers)")
@RequestMapping("/creditReqDetails")
public class CreditReqDetailsController {

    private final CreditReqDetailsService service;

    @Operation(
            summary = "Get CREDIT_REQUIREMENT_DETAILS by Id",
            description = "Returning object with full details where requested parameter is ID of credit requirement detail")
    @GetMapping("/id")
    public ResponseEntity<CreditReqDetails> findById(@RequestBody Long id){
        return service.findById(id);
    }

    @Operation(
            summary = "Get all CREDIT_REQUIREMENT_DETAILS from DB",
            description = "Returning collection of objects CREDIT_REQUIREMENT_DETAILS with full details")
    @GetMapping("/all")
    public ResponseEntity<List<CreditReqDetails>> findAll(){
        return service.findAll();
    }

    @Operation(
            summary = "Get CREDIT_REQUIREMENT_DETAILS by name",
            description = "Returning collection of objects CREDIT_REQUIREMENT_DETAILS where requested parameter is name")
    @GetMapping("/name")
    public ResponseEntity<List<CreditReqDetails>> findByName(@RequestBody String name){
        return service.findByName(name);
    }

    @Operation(
            summary = "Add CREDIT_REQUIREMENT_DETAILS",
            description = "Sending object CREDIT_REQUIREMENT_DETAILS to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<CreditReqDetails> add (@RequestBody @Valid CreditReqDetails creditReqDetails){

        creditReqDetails.setId(null);

        return new ResponseEntity<>(service.add(creditReqDetails), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update CREDIT_REQUIREMENT_DETAILS",
            description = "Sending object CREDIT_REQUIREMENT_DETAILS to DB for save. ID must be filled (by ID will be updated specific CREDIT_REQUIREMENT_DETAILS)")
    @PostMapping("/update")
    public ResponseEntity<CreditReqDetails> update(@RequestBody @Valid CreditReqDetails creditReqDetails){

        Long id = creditReqDetails.getId();

        service.update(creditReqDetails, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete CREDIT_REQUIREMENT_DETAILS",
            description = "Sending id of CREDIT_REQUIREMENT_DETAILS for delete object from DB")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id){
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
