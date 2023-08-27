package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CountryData;
import java.util.List;

@Repository
public interface CountryDataRepository extends JpaRepository<CountryData, Long> {

    @Query("SELECT c FROM CountryData c WHERE " +
            "LOWER(c.countryCode) LIKE lower(concat('%', :countryCode, '%')) " +
            "ORDER BY c.countryName ASC")
    List<CountryData> findByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT c FROM CountryData c WHERE " +
            "LOWER(c.countryName) LIKE lower(concat('%', :name, '%')) " +
            "ORDER BY c.countryName ASC")
    List<CountryData> findByName(@Param("name") String name);
}
