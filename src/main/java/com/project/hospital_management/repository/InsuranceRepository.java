package com.project.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.hospital_management.entity.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    // Custom queries (Examples):
    // Optional<Insurance> findByPolicyNumber(String policyNumber);
}
