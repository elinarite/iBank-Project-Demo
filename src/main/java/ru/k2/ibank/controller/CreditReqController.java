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
import ru.k2.ibank.model.dto.CreditReqByIdDto;
import ru.k2.ibank.model.entity.CreditReq;
import ru.k2.ibank.service.CreditReqService;
import javax.validation.Valid;

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
@Tag(name = "Credit requirements Data", description = "Controller for manage summary table 'Credit Offer' and 'Credit requirement details'")
@RequestMapping("/creditReq")
public class CreditReqController {

    private final CreditReqService creditReqService;

    @Operation(
            summary = "Get CREDIT REQUIREMENT by Id",
            description = "Returning object with full details where requested parameter is ID of CREDIT REQUIREMENT")
    @GetMapping("/id")
    public ResponseEntity<CreditReqByIdDto> findById(@RequestBody Long id){
        return creditReqService.findById(id);
    }

    @Operation(
            summary = "Add CREDIT REQUIREMENT",
            description = "Sending object CREDIT REQUIREMENT to DB for save. ID must be NULL (autoincrement in DB). This object is a link to CREDIT OFFER and CREDIT REQUIREMENT DETAILS")
    @PostMapping("/add")
    public ResponseEntity<CreditReq> add (@RequestBody @Valid CreditReq creditReq){
        return new ResponseEntity<>(creditReqService.add(creditReq), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update CREDIT REQUIREMENT",
            description = "Sending object CREDIT REQUIREMENT to DB for update. ID must be filled (by ID will be updated specific CREDIT REQUIREMENT)")
    @PostMapping("/update")
    public ResponseEntity<CreditReq> update(@RequestBody @Valid CreditReq creditReq){

        Long id = creditReq.getId();

        creditReqService.update(creditReq, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete CREDIT REQUIREMENT",
            description = "Sending id of CREDIT REQUIREMENT for delete object from DB")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id){

        creditReqService.deleteById(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
