package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CurrencyData;

import java.util.List;

@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
    @Query("SELECT cu FROM CurrencyData cu" +
            " ORDER BY cu.currencyCode ASC")
    List<CurrencyData> findAllOrderByCurrencyCodeAsc();

    @Query("SELECT cu FROM CurrencyData cu WHERE " +
            "(:currencyName is null or lower(cu.currencyName) LIKE lower(concat('%', :currencyName, '%'))) " +
            "AND (:currencyCode is null or lower(cu.currencyCode) LIKE lower(concat('%', :currencyCode, '%')))" +
            "ORDER BY cu.currencyCode ASC")
    List<CurrencyData> findCurrencyDataByParam(@Param("currencyName") String currencyName,
                                               @Param("currencyCode") String currencyCode);
}
