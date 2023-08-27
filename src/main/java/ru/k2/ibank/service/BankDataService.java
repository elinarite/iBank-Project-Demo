package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.entity.BankData;
import ru.k2.ibank.repo.BankDataRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BankDataService {
    private final BankDataRepository bankDataRepository;

    @Cacheable(cacheNames = {"bankById"}, key = "#id")
    public ResponseEntity<BankData> findById(Long id){

        BankData bankData = bankDataRepository.findById(id).get();

        return ResponseEntity.ok(bankData);
    }

    @Cacheable(cacheNames = {"banksAll"})
    public ResponseEntity<List<BankData>> findAll(){

        List<BankData>allBanks = bankDataRepository.findAll();

        if (allBanks.isEmpty()) {
            return new ResponseEntity<>(allBanks, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allBanks);
    }

    @Cacheable(cacheNames = {"banksBySwift"})
    public ResponseEntity<List<BankData>> findBySwift(String swift){

        List<BankData>bankBySwift = bankDataRepository.findBySwift(swift);

        if (bankBySwift.isEmpty()) {
            return new ResponseEntity<>(bankBySwift, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(bankBySwift);
    }

    @Cacheable(cacheNames = {"banksByName"})
    public ResponseEntity<List<BankData>> findByName(String name){

        List<BankData>bankByName = bankDataRepository.findByName(name);

        if (bankByName.isEmpty()){
            return new ResponseEntity<>(bankByName, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(bankByName);
    }

    @Cacheable(cacheNames = {"banksByIban"})
    public ResponseEntity<List<BankData>> findByIban(String iban){

        List<BankData>bankByIban = bankDataRepository.findByIban(iban);

        if (bankByIban.isEmpty()) {
            return new ResponseEntity<>(bankByIban, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(bankByIban);
    }

    @Cacheable(cacheNames = {"banksByCountry"})
    public ResponseEntity<List<BankData>> findByCountry(String country){

        List<BankData>bankByCountry = bankDataRepository.findByCountry(country);

        if (bankByCountry.isEmpty()) {
            return new ResponseEntity<>(bankByCountry, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(bankByCountry);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"banksAll"},allEntries = true),
            @CacheEvict(cacheNames = {"banksBySwift"},allEntries = true),
            @CacheEvict(cacheNames = {"banksByName"},allEntries = true),
            @CacheEvict(cacheNames = {"banksByIban"},allEntries = true),
            @CacheEvict(cacheNames = {"banksByCountry"},allEntries = true)})
    public BankData add(BankData bankData){
        bankData.setId(null);
        return bankDataRepository.save(bankData);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"banksAll"},allEntries = true),
            @CacheEvict(cacheNames = {"bankById"}, key = "#id"),
            @CacheEvict(cacheNames = {"banksBySwift"}, allEntries = true),
            @CacheEvict(cacheNames = {"banksByName"}, allEntries = true),
            @CacheEvict(cacheNames = {"banksByIban"}, allEntries = true),
            @CacheEvict(cacheNames = {"banksByCountry"}, allEntries = true)})
    public void update(BankData bankData, Long id){
        bankDataRepository.save(bankData);
    }

}
