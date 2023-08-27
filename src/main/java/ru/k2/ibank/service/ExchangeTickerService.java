package ru.k2.ibank.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.entity.ExchangeTicker;
import ru.k2.ibank.repo.ExchangeTickerRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExchangeTickerService {

    private final ExchangeTickerRepository repository;

    public ExchangeTickerService(ExchangeTickerRepository repository) {
        this.repository = repository;
    } 

    @Cacheable(cacheNames = {"exchangeTickerById"}, key = "#id")
    public ResponseEntity<ExchangeTicker> findById(Long id){

        ExchangeTicker exchangeTicker = repository.findById(id).get();

        return ResponseEntity.ok(exchangeTicker);
    }

    @Cacheable(cacheNames = {"exchangeTickerAll"})
    public ResponseEntity<List<ExchangeTicker>> findAll(){

        List<ExchangeTicker>allExchangeTickers = repository.findAll();

        if (allExchangeTickers.isEmpty()) {
            return new ResponseEntity<>(allExchangeTickers, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allExchangeTickers);
    }

    @Cacheable(cacheNames = {"exchangeTickerByCurrency"})
    public ResponseEntity<List<ExchangeTicker>> findByCurrency(String currency){

        List<ExchangeTicker>exchangeTickerByCurrency = repository.findByCurrency(currency);

        if (exchangeTickerByCurrency.isEmpty()){
            return new ResponseEntity<>(exchangeTickerByCurrency, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(exchangeTickerByCurrency);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"exchangeTickerAll"},allEntries = true),
            @CacheEvict(cacheNames = {"exchangeTickerByCurrency"},allEntries = true)})
    public ExchangeTicker add(ExchangeTicker exchangeTicker){

        exchangeTicker.setId(null);

        return repository.save(exchangeTicker);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"exchangeTickerById"}, key = "#id"),
            @CacheEvict(cacheNames = {"exchangeTickerAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"exchangeTickerByCurrency"}, allEntries = true)})
    public void update(ExchangeTicker exchangeTicker, Long id){
        repository.save(exchangeTicker);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {"exchangeTickerById"}, key = "#id"),
            @CacheEvict(cacheNames = {"exchangeTickerAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"exchangeTickerByCurrency"}, allEntries = true)})
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
