package com.nexus.backend.repository;

import com.nexus.backend.entity.preferences.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer> {

}
