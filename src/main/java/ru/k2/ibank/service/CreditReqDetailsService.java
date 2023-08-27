package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.entity.CreditReqDetails;
import ru.k2.ibank.repo.CreditReqDetailsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditReqDetailsService {

    private final CreditReqDetailsRepository repo;

    @Cacheable(cacheNames = {"creditReqDetailsById"}, key = "#id")
    public ResponseEntity<CreditReqDetails> findById(Long id){

        CreditReqDetails creditReqDetails = repo.findById(id).get();

        return ResponseEntity.ok(creditReqDetails);
    }

    @Cacheable(cacheNames = {"creditReqDetailsAll"})
    public ResponseEntity<List<CreditReqDetails>> findAll(){

        List<CreditReqDetails>allCreditReqDetails = repo.findAll();

        if (allCreditReqDetails.isEmpty()) {
            return new ResponseEntity<>(allCreditReqDetails, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allCreditReqDetails);
    }

    @Cacheable(cacheNames = {"CreditReqDetailsByName"})
    public ResponseEntity<List<CreditReqDetails>> findByName(String name){

        List<CreditReqDetails>creditReqDetailsByName = repo.findByName(name);

        if (creditReqDetailsByName.isEmpty()){
            return new ResponseEntity<>(creditReqDetailsByName, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(creditReqDetailsByName);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"creditReqDetailsAll"},allEntries = true),
            @CacheEvict(cacheNames = {"CreditReqDetailsByName"},allEntries = true)})
    public CreditReqDetails add(CreditReqDetails creditReqDetails){

        creditReqDetails.setId(null);

        return repo.save(creditReqDetails);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"creditReqDetailsAll"},allEntries = true),
            @CacheEvict(cacheNames = {"creditReqDetailsById"}, key = "#id"),
            @CacheEvict(cacheNames = {"CreditReqDetailsByName"}, allEntries = true)})
    public void update(CreditReqDetails creditReqDetails, Long id){
        repo.save(creditReqDetails);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"creditReqDetailsById"}, key = "#id"),
            @CacheEvict(cacheNames = {"creditReqDetailsAll"},allEntries = true),
            @CacheEvict(cacheNames = {"CreditReqDetailsByName"}, allEntries = true)})
    public void deleteById(Long id){
        repo.deleteById(id);
    }

}
