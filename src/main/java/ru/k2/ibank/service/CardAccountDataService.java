package ru.k2.ibank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.k2.ibank.model.dto.CardAccountDataDto;
import ru.k2.ibank.model.entity.CardAccountData;
import ru.k2.ibank.model.mapper.CardAccountDataMapper;
import ru.k2.ibank.model.search.CardAccountDataSearchValue;
import ru.k2.ibank.repo.CardAccountDataRepository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CardAccountDataService {

    private final CardAccountDataRepository cardAccountDataRepository;
    private final CardAccountDataMapper cardAccountDataMapper;

    public CardAccountDataService(CardAccountDataRepository cardAccountDataRepository, CardAccountDataMapper cardAccountDataMapper) {
        this.cardAccountDataRepository = cardAccountDataRepository;
        this.cardAccountDataMapper = cardAccountDataMapper;
    }

    @Cacheable(cacheNames = {"cardAccountDataDtoForUser"})
    public List<CardAccountDataDto> findAllByClientData(Long clientId) {
        List<CardAccountData> cardAccountDataList = cardAccountDataRepository.findAllByClientData(clientId);
        return cardAccountDataList.stream()
                .map(cardAccountDataMapper::cardAccountDataDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountDataDtoForUser"})
    public CardAccountDataDto findByIdAndClientId(Long accountId, Long clientId) {
        CardAccountData cardAccountData = cardAccountDataRepository.findByIdAndClientId(accountId, clientId);
        return cardAccountDataMapper.cardAccountDataDto(cardAccountData);
    }

    @Cacheable(cacheNames = {"cardAccountDataDtoForUser"})
    public CardAccountDataDto findByCurrencyIdAndClientId(Long currencyId, Long clientId) {
        CardAccountData cardAccountData = cardAccountDataRepository.findByCurrencyIdAndClientId(currencyId, clientId);
        return cardAccountDataMapper.cardAccountDataDto(cardAccountData);
    }


    @Cacheable(cacheNames = {"cardAccountDataById"}, key = "#id")
    public CardAccountData findById(Long id) {
        return cardAccountDataRepository.findById(id).get();

    }

    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public List<CardAccountDataDto> findAlLByCurrencyId(Long currencyId) {
        List<CardAccountData> allCardAccountData = cardAccountDataRepository.findByCurrencyId(currencyId);
        return allCardAccountData.stream()
                .map(cardAccountDataMapper::cardAccountDataDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountDataAllShort"})
    public List<CardAccountDataDto> findAllShort() {
        List<CardAccountData> allCardAccountData = cardAccountDataRepository.findAll();
        return allCardAccountData.stream()
                .map(cardAccountDataMapper::cardAccountDataDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountDataAllFull"})
    public ResponseEntity<List<CardAccountData>> findAll() {
        List<CardAccountData> allCardAccountData = cardAccountDataRepository.findAll();
        if (allCardAccountData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCardAccountData);
    }

    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public List<CardAccountDataDto> findAllOrderedByBalanceAsc() {
        List<CardAccountData> allCardAccountData = cardAccountDataRepository.findAllOrderByCardAccountBalanceAsc();
        return allCardAccountData.stream()
                .map(cardAccountDataMapper::cardAccountDataDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = {"cardAccountDataSearchByParam"})
    public Page<CardAccountData> search(CardAccountDataSearchValue cardAccountDataSearchValue) {
        String cardNumber = cardAccountDataSearchValue.getCardNumber();
        String cardAccountNumber = cardAccountDataSearchValue.getCardAccountNumber();
        Date issueDateFrom = cardAccountDataSearchValue.getIssueDateFrom();
        Date issueDateTo = cardAccountDataSearchValue.getIssueDateTo();
        Date expDateFrom = cardAccountDataSearchValue.getExpDateFrom();
        Date expDateTo = cardAccountDataSearchValue.getExpDateTo();
        BigDecimal balanceFrom = cardAccountDataSearchValue.getBalanceFrom();
        BigDecimal balanceTo = cardAccountDataSearchValue.getBalanceTo();
        boolean isBlocked = cardAccountDataSearchValue.isBlocked();
        String clientDataName = cardAccountDataSearchValue.getClientDataName();
        String clientDataSurname = cardAccountDataSearchValue.getClientDataSurname();
        String sortDirection = cardAccountDataSearchValue.getSortDirection();
        String sortColumn = cardAccountDataSearchValue.getSortColumn();
        Integer pageSize = cardAccountDataSearchValue.getPageSize();
        Integer pageNumber = cardAccountDataSearchValue.getPageNumber();

        Sort.Direction direction = sortDirection == null ||
                sortDirection.trim().length() == 0 ||
                sortDirection.equals("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn, "cardNumber");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return cardAccountDataRepository.search(
                cardNumber,
                cardAccountNumber,
                issueDateFrom,
                issueDateTo,
                expDateFrom,
                expDateTo,
                balanceFrom,
                balanceTo,
                isBlocked,
                clientDataName,
                clientDataSurname,
                pageRequest);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataDtoForUser"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataSearchByParam"}, allEntries = true)})
    public CardAccountData add(@NotNull CardAccountData cardAccountData) {
        cardAccountData.setId(null);
        return cardAccountDataRepository.save(cardAccountData);
    }


    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataById"}, key = "#id"),
            @CacheEvict(cacheNames = {"cardAccountDataDtoForUser"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataSearchByParam"}, allEntries = true)})
    public void update(@NotNull CardAccountData cardAccountData, Long id) {

        cardAccountDataRepository.save(cardAccountData);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"cardAccountDataById"}, key = "#id"),
            @CacheEvict(cacheNames = {"cardAccountDataDtoForUser"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAllShort"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataAll"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataFull"}, allEntries = true),
            @CacheEvict(cacheNames = {"cardAccountDataSearchByParam"}, allEntries = true)})
    public void updateIsBlockedByID(Long id, boolean isBlocked) {
        cardAccountDataRepository.updateIsBlockedByID(id, isBlocked);
    }

    /**
     * The method retrieves aggregated financial information for each client from the database.
     * The query selects the client's identifier, total balance of their card accounts,
     * average balance, and the count of their existing card accounts. This data is grouped by clients.
     */

    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public ResponseEntity<List<Object[]>> getTotalBalanceByClient() {
        return ResponseEntity.ok(cardAccountDataRepository.getTotalBalanceByClient());
    }

    /**
     * The method displays aggregated financial information for all card accounts.
     *
     * @totalBalanceSum - Sum of all balances of all card accounts.
     * @totalBlockedBalanceSum - Sum of balances of blocked card accounts.
     * @totalUnblockedBalanceSum - Sum of balances of unblocked card accounts.
     * @getTotalAccountCount - Total number of card accounts.
     * @getCountOfBlockedAccounts -  Number of blocked card accounts.
     * @getCountOfActiveAccounts - Number of unblocked card accounts.
     */

    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public ResponseEntity<Map<String, Object>> getBalanceSummary() {
        Map<String, Object> result = new HashMap<>();

        BigDecimal totalBalanceSum = cardAccountDataRepository.getTotalBalanceSum();
        BigDecimal totalBlockedBalanceSum = cardAccountDataRepository.getTotalBlockedBalanceSum();
        BigDecimal totalUnblockedBalanceSum = cardAccountDataRepository.getTotalUnblockedBalanceSum();
        BigDecimal getTotalAccountCount = cardAccountDataRepository.getTotalAccountCount();
        BigDecimal getCountOfBlockedAccounts = cardAccountDataRepository.getCountOfBlockedAccounts();
        BigDecimal getCountOfActiveAccounts = cardAccountDataRepository.getCountOfActiveAccounts();

        result.put("totalBalanceSum", totalBalanceSum);
        result.put("totalBlockedBalanceSum", totalBlockedBalanceSum);
        result.put("totalUnblockedBalanceSum", totalUnblockedBalanceSum);
        result.put("getTotalAccountCount", getTotalAccountCount);
        result.put("getCountOfBlockedAccounts", getCountOfBlockedAccounts);
        result.put("getCountOfActiveAccounts", getCountOfActiveAccounts);

        return ResponseEntity.ok(result);
    }

    /**
     * This provided method retrieves aggregated financial information for each currency from the database.
     * The query selects the currency code, total balance across card accounts, average balance, and the count of existing accounts.
     * These data are grouped by currency code.
     */
    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public ResponseEntity<List<Object[]>> getTotalBalanceByCurrencyData() {
        return ResponseEntity.ok(cardAccountDataRepository.getTotalBalanceByCurrencyData());
    }


    /**
     * This method provides aggregated financial information based on the client's identifier.
     *
     * @totalBalanceSum - Sum of all balances of the client's card accounts.
     * @totalBlockedBalanceSum - Sum of balances of the client's blocked card accounts.
     * @totalUnblockedBalanceSum - Sum of balances of the client's unblocked card accounts.
     * @getTotalAccountCount - Total number of card accounts belonging to the client.
     * @getCountOfBlockedAccounts - Number of blocked card accounts belonging to the client.
     * @getCountOfActiveAccounts - Number of unblocked card accounts belonging to the client.
     */

    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public ResponseEntity<Map<String, Object>> getBalanceSummaryByClientId(Long clientId) {
        Map<String, Object> clientBalance = new HashMap<>();

        BigDecimal totalBalanceSum = cardAccountDataRepository.getTotalBlockedBalanceSumForUser(clientId);
        BigDecimal totalUnblockedBalanceSum = cardAccountDataRepository.getTotalUnblockedBalanceSumForUser(clientId);
        BigDecimal totalBlockedBalanceSum = cardAccountDataRepository.getTotalBalanceForUser(clientId);
        BigDecimal getTotalAccountCountForUser = cardAccountDataRepository.getTotalAccountCountForUser(clientId);
        BigDecimal getTotalBlockedAccountCountForUser = cardAccountDataRepository.getTotalBlockedAccountCountForUser(clientId);
        BigDecimal getTotalActiveAccountCountForUser = cardAccountDataRepository.getTotalActiveAccountCountForUser(clientId);

        clientBalance.put("totalBalanceByClient", totalBalanceSum);
        clientBalance.put("totalBalanceSum", totalUnblockedBalanceSum);
        clientBalance.put("totalBlockedBalanceSum", totalBlockedBalanceSum);
        clientBalance.put("getTotalAccountCountForUser", getTotalAccountCountForUser);
        clientBalance.put("getTotalBlockedAccountCountForUser", getTotalBlockedAccountCountForUser);
        clientBalance.put("getTotalActiveAccountCountForUser", getTotalActiveAccountCountForUser);

        return ResponseEntity.ok(clientBalance);
    }
}