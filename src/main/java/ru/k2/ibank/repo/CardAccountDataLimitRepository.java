package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CardAccountDataLimit;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Repository
public interface CardAccountDataLimitRepository extends JpaRepository<CardAccountDataLimit, Long> {

    @Query("SELECT limit FROM CardAccountDataLimit limit " +
            "WHERE limit.cardAccountData.id = :ownerId")
    CardAccountDataLimit findByOwner(@Param("ownerId") Long ownerId);

    @Query("SELECT cal FROM CardAccountDataLimit cal " +
            "WHERE cal.cardAccountData.id IN " +
            "(SELECT cad.id FROM CardAccountData cad WHERE cad.clientData.id = :clientId)")
    List<CardAccountDataLimit> findByAccountClientId(@Param("clientId") Long clientId);

    //todo
    @Query("SELECT c FROM CardAccountDataLimit c WHERE" +
            "(:cardAccountNumber is null or :cardAccountNumber='' or c.cardAccountData.cardAccountNumber= :cardAccountNumber) and " +
            "(:monthlyLimitAmountFrom is null or c.monthlyLimitAmount>=:monthlyLimitAmountFrom) and " +
            "(:monthlyLimitAmountTo is null or c.monthlyLimitAmount<=:monthlyLimitAmountTo) and " +
            "(:monthlyLimitUsedFrom is null or c.monthlyLimitUsed>=:monthlyLimitUsedFrom) and " +
            "(:monthlyLimitUsedTo is null or c.monthlyLimitUsed<=:monthlyLimitUsedTo) and " +
            "(:dailyLimitAmountFrom is null or c.dailyLimitAmount>=:dailyLimitAmountFrom) and " +
            "(:dailyLimitAmountTo is null or c.dailyLimitAmount<=:dailyLimitAmountTo) and " +
            "(:dailyLimitUsedFrom is null or c.dailyLimitAmount>=:dailyLimitUsedFrom) and " +
            "(:dailyLimitUsedTo is null or c.dailyLimitUsed<=:dailyLimitUsedTo) and " +
            "(cast(:dailyDateFrom as timestamp ) is null or c.dailyLimitDate>=:dailyDateFrom) and " +
            "(cast(:dailyDateTo as timestamp ) is null or c.dailyLimitDate<=:dailyDateTo) " +
            "ORDER BY c.cardAccountData.cardAccountNumber ASC")
    List<CardAccountDataLimit> search(
            @Param("cardAccountNumber") String cardAccountNumber,
            @Param("monthlyLimitAmountFrom") BigDecimal monthlyLimitAmountFrom,
            @Param("monthlyLimitAmountTo") BigDecimal monthlyLimitAmountTo,
            @Param("monthlyLimitUsedFrom") BigDecimal monthlyLimitUsedFrom,
            @Param("monthlyLimitUsedTo") BigDecimal monthlyLimitUsedTo,
            @Param("dailyLimitAmountFrom") BigDecimal dailyLimitAmountFrom,
            @Param("dailyLimitAmountTo") BigDecimal dailyLimitAmountTo,
            @Param("dailyLimitUsedFrom") BigDecimal dailyLimitUsedFrom,
            @Param("dailyLimitUsedTo") BigDecimal dailyLimitUsedTo,
            @Param("dailyDateFrom") Date dailyDateFrom,
            @Param("dailyDateTo") Date dailyDateTo);


    @Modifying
    @Query("UPDATE CardAccountDataLimit limit " +
            "SET limit.dailyLimitAmount = COALESCE(:dailyLimit, limit.dailyLimitAmount), " +
            "limit.monthlyLimitAmount = COALESCE(:monthlyLimit, limit.monthlyLimitAmount) " +
            "WHERE limit.cardAccountData.id = :ownerId")
    void updateLimits(
            @Param("ownerId") Long ownerId,
            @Param("dailyLimit") BigDecimal dailyLimit,
            @Param("monthlyLimit") BigDecimal monthlyLimit);
}