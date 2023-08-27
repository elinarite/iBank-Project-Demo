package ru.k2.ibank.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.k2.ibank.model.dto.CreditReqByIdDto;
import ru.k2.ibank.model.entity.CreditReq;
import ru.k2.ibank.model.mapper.CreditReqMapper;
import ru.k2.ibank.repo.CreditReqRepository;
import javax.transaction.Transactional;

@Service
@Transactional
public class CreditReqService {

    private final CreditReqRepository creditReqRepository;
    private final CreditReqMapper creditReqMapper;

    public CreditReqService(CreditReqRepository creditReqRepository, CreditReqMapper creditReqMapper) {
        this.creditReqRepository = creditReqRepository;
        this.creditReqMapper = creditReqMapper;
    }

    @Cacheable(cacheNames = {"creditReqById"}, key = "#id")
    public ResponseEntity<CreditReqByIdDto> findById(Long id){

        CreditReq creditReq = creditReqRepository.findById(id).get();

        return ResponseEntity.ok(creditReqMapper.toCreditReq(creditReq));
    }

    public CreditReq add(CreditReq creditReq){

        creditReq.setId(null);

        return creditReqRepository.save(creditReq);
    }

    @Caching(evict = {@CacheEvict(cacheNames = {"creditReqById"}, key = "#id")})
    public void update(CreditReq creditReq, Long id){
        creditReqRepository.save(creditReq);
    }

    @Caching(evict = {@CacheEvict(cacheNames = {"creditReqById"}, key = "#id")})
    public void deleteById(Long id){
        creditReqRepository.deleteById(id);
    }
}
