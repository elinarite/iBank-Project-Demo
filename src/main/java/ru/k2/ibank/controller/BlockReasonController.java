package ru.k2.ibank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.k2.ibank.model.entity.BlockReason;
import ru.k2.ibank.service.BlockReasonService;
import ru.k2.ibank.util.exceptions.ErrorMessage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blockReason")
public class BlockReasonController {
    private final BlockReasonService blockReasonService;

    public BlockReasonController(BlockReasonService blockReasonService) {
        this.blockReasonService = blockReasonService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BlockReason> getById(@PathVariable Long id) {
        return blockReasonService.findById(id);
    }

    @GetMapping("/id")
    public ResponseEntity<BlockReason> findById(@RequestBody Long id) {
        return blockReasonService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlockReason>> findAll() {
        return blockReasonService.findAll();
    }

    @PostMapping("/search")
    public ResponseEntity<List<BlockReason>> search(@RequestParam(required = false) String blockReason,
                                                    @RequestParam(required = false) String blockDescription) {
        return blockReasonService.findBlockReasonByParam(blockReason, blockDescription);
    }

    @PostMapping("/add")
    public ResponseEntity<BlockReason> add(@Valid @RequestBody BlockReason blockReason) {
        return new ResponseEntity<>(blockReasonService.add(blockReason), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BlockReason> update(@Valid @RequestBody BlockReason blockReason) {
        Long id = blockReason.getId();
        ResponseEntity<BlockReason> existingBlockReason = blockReasonService.findById(id);
        if (id == existingBlockReason.getBody().getId()) {
            blockReasonService.update(blockReason, id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        blockReasonService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }
}