package com.nexus.backend.repository;

import com.nexus.backend.entity.preferences.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Integer> {

}
