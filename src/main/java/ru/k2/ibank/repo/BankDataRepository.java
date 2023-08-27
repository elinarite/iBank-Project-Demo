package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.BankData;
import java.util.List;

@Repository
public interface BankDataRepository extends JpaRepository<BankData, Long> {

    @Query("SELECT b FROM BankData b WHERE " +
            "LOWER(b.bankSwift) LIKE lower(concat('%', :swift, '%')) " +
            "ORDER BY b.bankName ASC")
    List<BankData> findBySwift(@Param("swift") String swift);

    @Query("SELECT b FROM BankData b WHERE " +
            "LOWER(b.bankName) LIKE lower(concat('%', :name, '%')) " +
            "ORDER BY b.bankName ASC")
    List<BankData> findByName(@Param("name") String name);

    @Query("SELECT b FROM BankData b WHERE " +
            "LOWER(b.bankIban) LIKE lower(concat('%', :iban, '%')) " +
            "ORDER BY b.bankName ASC")
    List<BankData> findByIban(@Param("iban") String iban);

    @Query("SELECT b FROM BankData b WHERE " +
            "LOWER(b.bankCountry.countryName) LIKE lower(concat('%', :country, '%')) " +
            "ORDER BY b.bankName ASC")
    List<BankData> findByCountry(@Param("country") String country);

}
