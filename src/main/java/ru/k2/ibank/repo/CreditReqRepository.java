package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CreditReq;
import java.util.List;

@Repository
public interface CreditReqRepository extends JpaRepository<CreditReq, Long> {

    @Query("SELECT c FROM CreditReq c WHERE " +
            "c.creditOffer.id = :offerId " +
            "ORDER BY c.creditReqDetails.creditReqName ASC")
    List<CreditReq> creditReqByOfferId(@Param("offerId") Long offerId);

    @Query("SELECT c FROM CreditReq c WHERE " +
            "c.creditReqDetails.id = :reqDetailsId " +
            "ORDER BY c.creditReqDetails.creditReqName ASC")
    List<CreditReq> creditReqByCreditReqDetailsId(@Param("reqDetailsId") Long reqDetailsId);

}
