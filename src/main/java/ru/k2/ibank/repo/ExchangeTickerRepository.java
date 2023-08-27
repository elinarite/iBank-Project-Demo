package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.ExchangeTicker;

import java.util.List;

@Repository
public interface ExchangeTickerRepository extends JpaRepository<ExchangeTicker, Long> {

    @Query("SELECT e FROM ExchangeTicker e WHERE " +
            "LOWER(e.currencyDataFrom.currencyCode) LIKE lower(concat('%', :currency, '%')) OR " +
            "LOWER(e.currencyDataFrom.currencyName) LIKE lower(concat('%', :currency, '%')) OR " +
            "LOWER(e.currencyDataTo.currencyCode) LIKE lower(concat('%', :currency, '%')) OR " +
            "LOWER(e.currencyDataTo.currencyName) LIKE lower(concat('%', :currency, '%')) " +
            "ORDER BY e.tickerName ASC")
    List<ExchangeTicker> findByCurrency(@Param("currency") String currency);

}
