package ru.k2.ibank.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CardAccountData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CardAccountDataRepository extends JpaRepository<CardAccountData, Long> {

    @Query("SELECT cad FROM CardAccountData cad WHERE cad.clientData.id = :clientId")
    List<CardAccountData> findAllByClientData(@Param("clientId") Long clientId);


    @Query("SELECT cad From CardAccountData cad WHERE cad.id = :accountId AND cad.clientData.id = :clientId")
    CardAccountData findByIdAndClientId(@Param("accountId") Long accountId, @Param("clientId") Long clientId);

    @Query("SELECT cad FROM CardAccountData cad " +
            "WHERE cad.currencyData.id = :currencyId AND cad.clientData.id = :clientId")
    CardAccountData findByCurrencyIdAndClientId(@Param("currencyId") Long currencyId, @Param("clientId") Long clientId);

    @Query("SELECT cad FROM CardAccountData cad " +
            "WHERE cad.currencyData.id = :currencyId")
    List<CardAccountData> findByCurrencyId(@Param("currencyId") Long currencyId);

    @Query("SELECT c From CardAccountData c" +
            " ORDER BY c.cardAccountBalance Asc")
    List<CardAccountData> findAllOrderByCardAccountBalanceAsc();

    @Query("SELECT c FROM CardAccountData c WHERE" +
            "(:cardNumber is null or :cardNumber='' or c.cardNumber= :cardNumber) and " +
            "(:cardAccountNumber is null or :cardNumber='' or c.cardAccountNumber= :cardAccountNumber) and " +
            "(cast(:issueDateFrom as timestamp ) is null or c.cardIssueDate>=:issueDateFrom) and " +
            "(cast(:issueDateTo as timestamp ) is null or c.cardIssueDate>=:issueDateFrom) and " +
            "(cast(:expDateFrom as timestamp ) is null or c.cardExpirationDate>=:expDateFrom) and " +
            "(cast(:expDateTo as timestamp ) is null or c.cardExpirationDate>=:expDateTo) and " +
            "(:balanceFrom is null or c.cardAccountBalance>=:balanceFrom) and " +
            "(:balanceTo is null or c.cardAccountBalance<=:balanceTo) and " +
            "(:isBlocked is null or c.isBlocked = :isBlocked) and " +
            "(:clientDataName is null or :clientDataName='' or lower(c.clientData.clientName) like lower (concat('%', :clientDataName, '%'))) and " +
            "(:clientDataSurname is null or :clientDataSurname='' or lower(c.clientData.clientSurname) like lower (concat('%', :clientDataSurname, '%')))"
    )
    Page<CardAccountData> search(
            @Param("cardNumber") String cardNumber,
            @Param("cardAccountNumber") String cardAccountNumber,
            @Param("issueDateFrom") Date issueDateFrom,
            @Param("issueDateTo") Date issueDateTo,
            @Param("expDateFrom") Date expDateFrom,
            @Param("expDateTo") Date expDateTo,
            @Param("balanceFrom") BigDecimal balanceFrom,
            @Param("balanceTo") BigDecimal balanceTo,
            @Param("isBlocked") Boolean isBlocked,
            @Param("clientDataName") String clientDataName,
            @Param("clientDataSurname") String clientDataSurname,
            Pageable page);

    @Modifying
    @Query("UPDATE CardAccountData c " +
            "SET c.isBlocked = :isBlocked " +
            "WHERE c.id = :id")
    void updateIsBlockedByID(@Param("id") Long id, @Param("isBlocked") boolean isBlocked);

    @Query("SELECT c.clientData.id, SUM(c.cardAccountBalance) AS total_balance, AVG(c.cardAccountBalance) AS average_balace, COUNT(c.cardAccountNumber) AS count_account " +
            "FROM CardAccountData c " +
            "GROUP BY c.clientData")
    List<Object[]> getTotalBalanceByClient();

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c")
    BigDecimal getTotalBalanceSum();

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = true")
    BigDecimal getTotalBlockedBalanceSum();

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = false")
    BigDecimal getTotalUnblockedBalanceSum();

    @Query("SELECT c.currencyData.currencyCode,  SUM(c.cardAccountBalance) AS total_balance, AVG(c.cardAccountBalance) AS average_balace, COUNT(c.currencyData) AS count_by_currency " +
            "FROM CardAccountData c " +
            "GROUP BY c.currencyData.currencyCode")
    List<Object[]> getTotalBalanceByCurrencyData();

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = true AND c.clientData.id = :clientId")
    BigDecimal getTotalBlockedBalanceSumForUser(@Param("clientId") Long clientId);

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = false AND c.clientData.id = :clientId")
    BigDecimal getTotalUnblockedBalanceSumForUser(@Param("clientId") Long clientId);

    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.clientData.id = :clientId")
    BigDecimal getTotalBalanceForUser(@Param("clientId") Long clientId);

    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.clientData.id = :clientId")
    BigDecimal getTotalAccountCountForUser(@Param("clientId") Long clientId);

    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = true AND c.clientData.id = :clientId")
    BigDecimal getTotalBlockedAccountCountForUser(@Param("clientId") Long clientId);

    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = false AND c.clientData.id = :clientId")
    BigDecimal getTotalActiveAccountCountForUser(@Param("clientId") Long clientId);


    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = true")
    BigDecimal getCountOfBlockedAccounts();

    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = false")
    BigDecimal getCountOfActiveAccounts();


    @Query("SELECT COUNT(c) FROM CardAccountData c")
    BigDecimal getTotalAccountCount();

}