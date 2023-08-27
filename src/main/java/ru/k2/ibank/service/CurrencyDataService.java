package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.k2.ibank.model.entity.CurrencyData;
import ru.k2.ibank.repo.CurrencyDataRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CurrencyDataService {
    private final CurrencyDataRepository currencyDataRepository;

    public CurrencyDataService(CurrencyDataRepository currencyDataRepository) {
        this.currencyDataRepository = currencyDataRepository;
    }

    @Cacheable(cacheNames = {"currencyDataById"}, key = "#id")
    public ResponseEntity<CurrencyData> findById(Long id) {
        CurrencyData currencyData = currencyDataRepository.findById(id).get();

        return ResponseEntity.ok(currencyData);
    }

    @Cacheable(cacheNames = {"currencyDataAll"})
    public ResponseEntity<List<CurrencyData>>findAll() {
        List<CurrencyData> allCurrencies = currencyDataRepository.findAllOrderByCurrencyCodeAsc();
        if (allCurrencies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCurrencies);
    }

    @Cacheable(cacheNames = "currencyDataByParam")
    public ResponseEntity<List<CurrencyData>> findCurrencyDataByParam (String currencyName, String currencyCode) {
        List<CurrencyData> currencyByParam = currencyDataRepository.findCurrencyDataByParam(currencyName, currencyCode);
        if (currencyByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(currencyByParam);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"currencyDataAll"},allEntries = true),
            @CacheEvict(cacheNames = {"currencyDataByParam"},allEntries = true)})
    public CurrencyData add(@NotNull CurrencyData currencyData) {
        currencyData.setId(null);
        return currencyDataRepository.save(currencyData);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"currencyDataById"},key = "#id"),
            @CacheEvict(cacheNames = {"currencyDataAll"},allEntries = true),
            @CacheEvict(cacheNames = {"currencyDataByParam"},allEntries = true)})
    public CurrencyData update(@NotNull CurrencyData currencyData, Long id) {
        return currencyDataRepository.save(currencyData);
    }
}
