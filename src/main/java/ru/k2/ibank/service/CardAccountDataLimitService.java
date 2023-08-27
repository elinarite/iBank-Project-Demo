package ru.k2.ibank.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.k2.ibank.model.dto.CardAccountDataLimitDto;
import ru.k2.ibank.model.entity.CardAccountDataLimit;
import ru.k2.ibank.model.mapper.CardAccountDataLimitMapper;
import ru.k2.ibank.model.search.CardAccountDataLimitSearchValue;
import ru.k2.ibank.repo.CardAccountDataLimitRepository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class CardAccountDataLimitService {
    private final CardAccountDataLimitRepository cardAccountDataLimitRepository;
    private final CardAccountDataLimitMapper cardAccountDataLimitMapper;

    public CardAccountDataLimitService(CardAccountDataLimitRepository cardAccountDataLimitRepository, CardAccountDataLimitMapper cardAccountDataLimitMapper) {
        this.cardAccountDataLimitRepository = cardAccountDataLimitRepository;
        this.cardAccountDataLimitMapper = cardAccountDataLimitMapper;
    }

    @Cacheable(cacheNames = {"cardAccountId"})
    public CardAccountDataLimit findByOwner(Long ownerId) {
        return cardAccountDataLimitRepository.findByOwner(ownerId);
    }

    @Cacheable(cacheNames = {"cardAccountId"})
    public ResponseEntity<CardAccountDataLimitDto> findByOwnerForUser(Long ownerId) {
        CardAccountDataLimit entity = cardAccountDataLimitRepository.findByOwner(ownerId);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cardAccountDataLimitMapper.cardAccountDataLimitDto(entity));
    }

    @Cacheable(cacheNames = {"cardAccountDataLimitById"}, key = "#id")
    public CardAccountDataLimit findById(Long id) {
        return cardAccountDataLimitRepository.findById(id).get();
    }

    @Cacheable(cacheNames = {"cardAccountDataLimitAllShort"})
    public List<CardAccountDataLimitDto> findAllShort() {
        List<CardAccountDataLimit> dataLimits = cardAccountDataLimitRepository.findAll();
        return dataLimits.stream()
                .map(cardAccountDataLimitMapper::cardAccountDataLimitDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountDataLimitAllFull"})
    public ResponseEntity<List<CardAccountDataLimit>> findAll() {
        List<CardAccountDataLimit> allCardAccountDataLimits = cardAccountDataLimitRepository.findAll();
        if (allCardAccountDataLimits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCardAccountDataLimits);
    }

    @Cacheable(cacheNames = {"cardAccountClientID"})
    public ResponseEntity<List<CardAccountDataLimit>> findByCardAccountDataId(Long accountId) {
        List<CardAccountDataLimit> cardLimitsByClientId = cardAccountDataLimitRepository.findByAccountClientId(accountId);
        if (cardLimitsByClientId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cardLimitsByClientId);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {"cardAccountDataLimitSearch"})
    public List<CardAccountDataLimitDto> search(CardAccountDataLimitSearchValue card) {
        List<CardAccountDataLimit> limitList = cardAccountDataLimitRepository.search(
                card.getCardAccountNumber(),
                card.getMonthlyLimitAmountFrom(),
                card.getMonthlyLimitAmountTo(),
                card.getMonthlyLimitUsedFrom(),
                card.getMonthlyLimitUsedTo(),
                card.getDailyLimitAmountFrom(),
                card.getDailyLimitAmountTo(),
                card.getDailyLimitUsedFrom(),
                card.getDailyLimitUsedTo(),
                card.getDailyDateFrom(),
                card.getDailyDateTo()
        );

        return limitList.stream()
                .map(cardAccountDataLimitMapper::cardAccountDataLimitDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountStatusByParam"}, allEntries = true)})
    public CardAccountDataLimit add(@NotNull CardAccountDataLimit cardAccountDataLimit) {

        cardAccountDataLimit.setId(null);

        return cardAccountDataLimitRepository.save(cardAccountDataLimit);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataLimitAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountClientID"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitSearch"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllFull"}, allEntries = true)})
    public void updateLimits(Long ownerId, BigDecimal monthlyLimit, BigDecimal dailyLimit) {
        cardAccountDataLimitRepository.updateLimits(ownerId, monthlyLimit, dailyLimit);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataLimitById"}, key = "#id"),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountClientID"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitSearch"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true)})
    public void update(@NotNull CardAccountDataLimit cardAccountDataLimit, Long id) {
        cardAccountDataLimitRepository.save(cardAccountDataLimit);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataLimitById"}, key = "#id"),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitAllFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountClientID"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataLimitSearch"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountId"}, allEntries = true)})
    public void deleteById(Long id) {
        cardAccountDataLimitRepository.deleteById(id);
    }
}