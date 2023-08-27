package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.CardAccountStatus;

import java.util.Date;
import java.util.List;


@Repository
public interface CardAccountStatusRepository extends JpaRepository<CardAccountStatus, Long> {

    @Query("SELECT cas FROM CardAccountStatus cas WHERE cas.cardAccountData.clientData.id = :ownerId")
    CardAccountStatus findByOwner(@Param("ownerId") Long ownerId);

    @Query("SELECT c From CardAccountStatus c WHERE" +
            "(:managerId is null or c.managerData.id = :managerId) and " +
            "(:blockReasonId is null or c.blockReason.id = :blockReasonId) and " +
            "(cast(:dateBlockFrom as timestamp )is null or c.cardBlockDate>=:dateBlockFrom) and " +
            "(cast(:dateBlockTo as timestamp ) is null or c.cardBlockDate<=:dateBlockTo) and " +
            "(cast(:dateUnblockFrom as timestamp ) is null or c.cardUnblockDate>=:dateUnblockFrom) and " +
            "(cast(:dateUnblockTo as timestamp ) is null or c.cardUnblockDate<=:dateUnblockTo)")
    List<CardAccountStatus> searchByParam(@Param("managerId") Long managerId,
                                          @Param("blockReasonId") Long blockReasonId,
                                          @Param("dateBlockFrom") Date dateBlockFrom,
                                          @Param("dateBlockTo") Date dateBlockTo,
                                          @Param("dateUnblockFrom") Date dateUnblockFrom,
                                          @Param("dateUnblockTo") Date dateUnblockTo);

    @Modifying
    @Query("UPDATE CardAccountStatus c " +
            "SET c.cardUnblockDate = cast(:cardUnblockDate as timestamp)" +
            "WHERE c.id = :id")
    void updateDays(@Param("id") Long id,
                    @Param("cardUnblockDate") Date cardUnblockDate);

}