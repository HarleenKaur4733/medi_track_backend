package com.project.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.hospital_management.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Example custom query:
    // Optional<Department> findByName(String name);
}
