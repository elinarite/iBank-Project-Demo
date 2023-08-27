package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.entity.CountryData;
import ru.k2.ibank.repo.CountryDataRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryDataService {

    private final CountryDataRepository countryDataRepository;

    @Cacheable(cacheNames = {"countryById"}, key = "#id")
    public ResponseEntity<CountryData> findById(Long id){

        CountryData countryData = countryDataRepository.findById(id).get();

        return ResponseEntity.ok(countryData);
    }

    @Cacheable(cacheNames = {"countryAll"})
    public ResponseEntity<List<CountryData>> findAll(){

        List<CountryData>allCountries = countryDataRepository.findAll();

        if (allCountries.isEmpty()) {
            return new ResponseEntity<>(allCountries, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allCountries);
    }

    @Cacheable(cacheNames = {"countriesByCode"})
    public ResponseEntity<List<CountryData>> findByCountryCode(String countryCode){

        List<CountryData> countriesByCode = countryDataRepository.findByCountryCode(countryCode);

        if (countriesByCode.isEmpty()) {
            return new ResponseEntity<>(countriesByCode, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(countriesByCode);
    }

    @Cacheable(cacheNames = {"countriesByName"})
    public ResponseEntity<List<CountryData>> findByName(String name){

        List<CountryData>countriesByName = countryDataRepository.findByName(name);

        if (countriesByName.isEmpty()){
            return new ResponseEntity<>(countriesByName, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(countriesByName);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"countryAll"},allEntries = true),
            @CacheEvict(cacheNames = {"countriesByCode"},allEntries = true),
            @CacheEvict(cacheNames = {"countriesByName"},allEntries = true)})
    public CountryData add(CountryData countryData){

        countryData.setId(null);

        return countryDataRepository.save(countryData);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"countryById"}, key = "#id"),
            @CacheEvict(cacheNames = {"countryAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"countriesByCode"}, allEntries = true),
            @CacheEvict(cacheNames = {"countriesByName"}, allEntries = true)})
    public void update(CountryData countryData, Long id){
        countryDataRepository.save(countryData);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"countryById"}, key = "#id"),
            @CacheEvict(cacheNames = {"countryAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"countriesByCode"}, allEntries = true),
            @CacheEvict(cacheNames = {"countriesByName"}, allEntries = true)})
    public void deleteById(Long id){
        countryDataRepository.deleteById(id);
    }
}
