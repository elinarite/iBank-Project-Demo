package ru.k2.ibank.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.k2.ibank.model.entity.ClientData;
import ru.k2.ibank.model.entity.ManagerData;


import java.util.Date;
import java.util.List;

@Repository
public interface ManagerDataRepository extends JpaRepository<ManagerData, Long> {

    @Query("SELECT m FROM ManagerData m" +
            " ORDER BY m.managerSurname ASC")
    List<ManagerData> findAllOrderByManagerSurnameAsc();

    @Query("SELECT c FROM ClientData c " +
            "WHERE c.clientManager.id = :managerId " +
            "ORDER BY c.clientSurname ASC")
    List<ClientData> findByClientManagerIdOrderByClientSurnameAsc(@Param("managerId") Long managerId);

    @Query("SELECT m.id,  m.managerSurname, m.managerName, COUNT(c) as numClients " +
            "FROM ManagerData m " +
            "LEFT JOIN m.clients c " +
            "GROUP BY m.id " +
            "ORDER BY numClients")
    List<Object[]> findManagerWithCountClients();

    List<ManagerData> findByManagerDismissDateIsNotNullOrderByManagerSurnameAsc();

    List<ManagerData> findByManagerDismissDateIsNullOrderByManagerSurnameAsc();

    @Query("SELECT m FROM ManagerData m WHERE " +
            "(:managerName is null or :managerName='' or lower(m.managerName) like lower(concat('%',:managerName,'%'))) and" +
            "(:managerSurname is null or :managerSurname='' or lower(m.managerSurname) like lower(concat('%',:managerSurname,'%'))) and " +
            "(:managerMiddlename is null or :managerMiddlename='' or lower(m.managerMiddlename) like lower(concat('%',:managerMiddlename,'%')))" +
            "ORDER BY m.managerSurname ASC")
    List<ManagerData> searchByName(@Param("managerName") String managerName, //todo
                                   @Param("managerSurname") String managerSurname,
                                   @Param("managerMiddlename") String managerMiddlename);

    @Query(value = "SELECT m FROM ManagerData m WHERE " +
            "(:managerName is null or :managerName='' or lower(m.managerName) like lower(concat('%',:managerName,'%'))) and" +
            "(:managerSurname is null or :managerSurname='' or lower(m.managerSurname) like lower(concat('%',:managerSurname,'%'))) and " +
            "(:managerMiddlename is null or :managerMiddlename='' or lower(m.managerMiddlename) like lower(concat('%',:managerMiddlename,'%'))) and " +
            "(:managerPassport is null or :managerPassport='' or lower(m.managerPassport) like lower(concat('%',:managerPassport,'%'))) and " +
            "(:managerPassportDepartmentCode is null or :managerPassportDepartmentCode='' or lower(m.managerPassportDepartmentCode) = lower(:managerPassportDepartmentCode))" +
            "ORDER BY m.managerSurname ASC")
    List<ManagerData> searchByNameAndPassport(@Param("managerName") String managerName,
                                              @Param("managerSurname") String managerSurname,
                                              @Param("managerMiddlename") String managerMiddlename,
                                              @Param("managerPassport") String managerPassport,
                                              @Param("managerPassportDepartmentCode") String managerPassportDepartmentCode);


    @Query("SELECT m FROM ManagerData m WHERE " +
            "(:managerName is null or :managerName='' or lower(m.managerName) like lower(concat('%',:managerName,'%'))) and " +
            "(:managerSurname is null or :managerSurname='' or lower(m.managerSurname) like lower(concat('%',:managerSurname,'%'))) and " +
            "(:managerMiddlename is null or :managerMiddlename='' or lower(m.managerMiddlename) like lower(concat('%',:managerMiddlename,'%'))) and " +
            "(cast(:managerBirthday as date) is null or m.managerBirthday= :managerBirthday) and " +
            "(:managerGender is null or lower(m.managerGender) = lower(:managerGender)) and " +
            "(:managerPassport is null or :managerPassport='' or lower(m.managerPassport) like lower(concat('%',:managerPassport,'%'))) and " +
            "(cast(:managerPassportIssDate as date) is null or m.managerPassportIssDate= :managerPassportIssDate) and " +
            "(cast(:managerPassportExpDate as date) is null or m.managerPassportExpDate= :managerPassportExpDate) and " +
            "(:managerPassportDepartmentCode is null or :managerPassportDepartmentCode='' or lower(m.managerPassportDepartmentCode) = lower(:managerPassportDepartmentCode)) and" +
            "(cast(:managerHiringDate as timestamp )is null or m.managerHiringDate= :managerHiringDate) and " +
            "(cast(:managerDismissDate as timestamp )is null or m.managerDismissDate= :managerDismissDate) and " +
            "(:managerEmail is null or :managerEmail='' or m.managerEmail= :managerEmail) and " +
            "(:managerAddress is null or :managerAddress='' or lower(m.managerAddress) like lower(concat('%',:managerAddress,'%'))) and" +
            "(:managerPhone is null or :managerPhone='' or lower(m.managerPhone) like lower(concat('%',:managerPhone,'%'))) " +
            "ORDER BY m.managerSurname ASC")
    Page<ManagerData> search(
            @Param("managerName") String managerName,
            @Param("managerSurname") String managerSurname,
            @Param("managerMiddlename") String managerMiddlename,
            @Param("managerBirthday") Date managerBirthday,
            @Param("managerGender") char managerGender,
            @Param("managerPassport") String managerPassport,
            @Param("managerPassportIssDate") Date managerPassportIssDate,
            @Param("managerPassportExpDate") Date managerPassportExpDate,
            @Param("managerPassportDepartmentCode") String managerPassportDepartmentCode,
            @Param("managerHiringDate") Date managerHiringDate,
            @Param("managerDismissDate") Date managerDismissDate,
            @Param("managerEmail") String managerEmail,
            @Param("managerAddress") String managerAddress,
            @Param("managerPhone") String managerPhone,
            Pageable page);

}