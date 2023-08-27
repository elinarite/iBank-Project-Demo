package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CreditOffer;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, Long> {

    @Query("SELECT c FROM CreditOffer c WHERE " +
            "LOWER(c.creditName) LIKE lower(concat('%', :name, '%')) " +
            "ORDER BY c.creditName ASC")
    List<CreditOffer> findByName(@Param("name") String name);

    @Query("SELECT c FROM CreditOffer c WHERE " +
            "c.creditValidTill > CURRENT_TIMESTAMP  " +
            "ORDER BY c.creditName ASC")
    List<CreditOffer> findAllValid();

    @Query("SELECT c FROM CreditOffer c WHERE " +
            "c.creditValidTill < CURRENT_TIMESTAMP  " +
            "ORDER BY c.creditName ASC")
    List<CreditOffer> findAllExpired();

    @Query("SELECT c FROM CreditOffer c WHERE " +
            "(c.creditValidTill > CURRENT_TIMESTAMP) AND " +
            "(:creditName is NULL OR LOWER(c.creditName) LIKE lower(concat('%', :creditName, '%'))) AND " +
            "(:creditTerm is NULL OR (c.creditMinTerm < :creditTerm AND c.creditMaxTerm > :creditTerm)) AND" +
            "(:creditAmount is NULL OR (c.creditMinAmount < :creditAmount AND c.creditMaxAmount > :creditAmount)) AND " +
            "(:creditCurrency is NULL OR (LOWER(c.currencyData.currencyCode) LIKE lower(concat('%', :creditCurrency, '%')) )) " +
            "ORDER BY c.creditName ASC")
    List<CreditOffer> findByParams(@Param("creditName") String creditName,
                                   @Param("creditTerm") Short creditTerm,
                                   @Param("creditAmount") BigDecimal creditAmount,
                                   @Param("creditCurrency") String creditCurrency);

}
