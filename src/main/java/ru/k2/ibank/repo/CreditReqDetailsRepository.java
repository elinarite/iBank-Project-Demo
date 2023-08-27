package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CreditReqDetails;

import java.util.List;

@Repository
public interface CreditReqDetailsRepository extends JpaRepository<CreditReqDetails, Long> {

    @Query("SELECT c FROM CreditReqDetails c WHERE " +
            "LOWER(c.creditReqName) LIKE lower(concat('%', :name, '%')) " +
            "ORDER BY c.creditReqName ASC")
    List<CreditReqDetails> findByName(@Param("name") String name);

}
