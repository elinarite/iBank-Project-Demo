package ru.k2.ibank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.BlockReason;

import java.util.List;

@Repository
public interface BlockReasonRepository extends JpaRepository<BlockReason, Long> {

    @Query("SELECT br FROM BlockReason br" +
            " ORDER BY br.blockReason ASC")
    List<BlockReason> findAllOrderByBlockReasonAsc();

    @Query("SELECT br FROM BlockReason br WHERE " +
            "(:blockReason is null or :blockReason='' or lower(br.blockReason) LIKE lower(concat('%', :blockReason, '%'))) and" +
            "(:blockDescription is null or :blockDescription='' or lower(br.blockDescription) LIKE lower(concat('%', :blockDescription, '%')))" +
            "ORDER BY br.blockReason ASC")
    List<BlockReason> findBlockReasonByParam(@Param("blockReason") String blockReason,
                                             @Param("blockDescription") String blockDescription);
}