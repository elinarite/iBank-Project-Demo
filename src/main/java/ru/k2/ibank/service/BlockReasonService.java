package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.k2.ibank.model.entity.BlockReason;
import ru.k2.ibank.repo.BlockReasonRepository;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class BlockReasonService {

    private final BlockReasonRepository blockReasonRepository;

    public BlockReasonService(BlockReasonRepository blockReasonRepository) {
        this.blockReasonRepository = blockReasonRepository;
    }

    @Cacheable(cacheNames = {"blockReasonById"}, key = "#id")
    public ResponseEntity<BlockReason> findById(Long id) {
        BlockReason blockReason = blockReasonRepository.findById(id).get();

        return ResponseEntity.ok(blockReason);
    }

    @Cacheable(cacheNames = {"blockReasonsAll"})
    public ResponseEntity<List<BlockReason>> findAll() {
        List<BlockReason> allBlockReasons = blockReasonRepository.findAllOrderByBlockReasonAsc();
        if (allBlockReasons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allBlockReasons);
    }

    @Cacheable(cacheNames = {"blockReasonsByParam"})
    public ResponseEntity<List<BlockReason>> findBlockReasonByParam(String blockReason, String blockDescription) {
        List<BlockReason> blockReasonByParam = blockReasonRepository.findBlockReasonByParam(blockReason, blockDescription);
        if (blockReasonByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(blockReasonByParam);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"blockReasonsAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"blockReasonsByParam"}, allEntries = true)})
    public BlockReason add(@NotNull BlockReason blockReason) {

        blockReason.setId(null);

        return blockReasonRepository.save(blockReason);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"blockReasonById"}, key = "#id"),
            @CacheEvict(cacheNames = {"blockReasonsAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"blockReasonsByParam"}, allEntries = true)})
    public void update(@NotNull BlockReason blockReason, Long id) {
        blockReasonRepository.save(blockReason);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"blockReasonById"}, key = "#id"),
            @CacheEvict(cacheNames = {"blockReasonsAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"blockReasonsByParam"}, allEntries = true)})
    public void deleteById(Long id) {
        blockReasonRepository.deleteById(id);
    }
}