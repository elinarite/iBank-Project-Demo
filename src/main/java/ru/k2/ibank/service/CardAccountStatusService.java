package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ru.k2.ibank.model.dto.CardAccountStatusDto;
import ru.k2.ibank.model.entity.CardAccountStatus;
import ru.k2.ibank.model.mapper.CardAccountStatusMapper;
import ru.k2.ibank.repo.CardAccountStatusRepository;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardAccountStatusService {
    private final CardAccountStatusRepository cardAccountStatusRepository;
    private final CardAccountStatusMapper cardAccountStatusMapper;

    public CardAccountStatusService(CardAccountStatusRepository cardAccountStatusRepository, CardAccountStatusMapper cardAccountStatusMapper) {
        this.cardAccountStatusRepository = cardAccountStatusRepository;
        this.cardAccountStatusMapper = cardAccountStatusMapper;
    }

    @Cacheable(cacheNames = {"cardAccountId"})
    public ResponseEntity<CardAccountStatusDto> findByOwnerForUser(Long ownerId) {
        CardAccountStatus entity = cardAccountStatusRepository.findByOwner(ownerId);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cardAccountStatusMapper.cardAccountStatusDto(entity));
    }

    @Cacheable(cacheNames = {"cardAccountStatusById"}, key = "#id")
    public CardAccountStatus findById(Long id) {
        return cardAccountStatusRepository.findById(id).get();

    }

    @Cacheable(cacheNames = {"cardAccountStatusAllFull"})
    public ResponseEntity<List<CardAccountStatus>> findAll() {
        List<CardAccountStatus> allCardAccountStatus = cardAccountStatusRepository.findAll();
        if (allCardAccountStatus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCardAccountStatus);
    }

    @Cacheable(cacheNames = {"cardAccountStatusAllShort"})
    public List<CardAccountStatusDto> findAllShort() {
        List<CardAccountStatus> cardStatus = cardAccountStatusRepository.findAll();
        return cardStatus.stream()
                .map(cardAccountStatusMapper::cardAccountStatusDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountStatusByParam"})
    public ResponseEntity<List<CardAccountStatus>> searchByParam(Long managerId, Long blockReasonId,
                                                                 Date dateBlockFrom, Date dateBlockTo,
                                                                 Date dateUnblockFrom, Date dateUnblockTo) {

        List<CardAccountStatus> cardStatusByParam = cardAccountStatusRepository.searchByParam(managerId, blockReasonId,
                dateBlockFrom, dateBlockTo,
                dateUnblockFrom, dateUnblockTo);
        if (cardStatusByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cardStatusByParam);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusByParam"}, allEntries = true)})
    public void updateUnblockDay(Long id, Date cardUnblockDate) {
        cardAccountStatusRepository.updateDays(id, cardUnblockDate);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusByParam"}, allEntries = true)})
    public CardAccountStatus add(@NotNull CardAccountStatus cardAccountStatus) {

        cardAccountStatus.setId(null);

        return cardAccountStatusRepository.save(cardAccountStatus);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountStatusById"}, key = "#id"),
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusByParam"}, allEntries = true)})
    public CardAccountStatus update(@NotNull CardAccountStatus cardAccountStatus, Long id) {
        return cardAccountStatusRepository.save(cardAccountStatus);
    }
}