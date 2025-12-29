package com.project.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospital_management.dto.BloodGroupTypeResponseEntity;
import com.project.hospital_management.entity.Patient;

import java.time.LocalDate;
import java.util.List;
import com.project.hospital_management.entity.type.BloodGroupType;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // JPA query methods (explore more on web)
    List<Patient> findByNameOrEmail(String name, String email);

    // JPQL
    // JPQL does NOT use table names
    // JPQL uses ENTITY CLASS NAMES
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    // using variable :
    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    // group by in jpql
    // @Query("SELECT p.bloodGroup, Count(p) FROM Patient p GROUP BY p.bloodGroup")
    // List<Object[]> countEachBloodGroupType();

    // Re-writing above query using dto
    // DTO projection preferred over Object[] because:
    // Projection doesnot work with nativvve query
    // 1) Gives type safety & readability
    // 2) Avoids manual casting from Object[]
    // 3) Cleaner to use & maintain
    // 4) Prevents exposing entire entity to API
    // 5) Allows custom response structure

    @Query("SELECT new com.project.hospital_management.dto.BloodGroupTypeResponseEntity(p.bloodGroup, COUNT(p)) FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupTypeResponseEntity> countEachBloodGroupType();

    // native query : telling hibenmate that its already sql, no need to convert it
    // use actual db name, not entity name
    @Query(value = "SELECT * from patient_data", nativeQuery = true)
    List<Patient> findAllPatients();

    // @Modifying → Tells Spring this is an UPDATE/DELETE query (not a SELECT).
    // @Transactional → Required to commit update/delete queries to DB.
    // Without @Modifying → Spring treats @Query as SELECT and throws error.
    // Without @Transactional → Query may run but won't commit properly.
    // Use both annotations for queries that change data.
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointment")
    List<Patient> findAllPatientsWithAppointments();

}
