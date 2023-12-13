package com.nexus.backend.repository;

import com.nexus.backend.entity.Compliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplianceRepository extends JpaRepository<Compliance, Integer> {

}
