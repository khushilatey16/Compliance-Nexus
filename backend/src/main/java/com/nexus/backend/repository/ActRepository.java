package com.nexus.backend.repository;

import com.nexus.backend.entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActRepository extends JpaRepository<Act, Integer> {

    List<Act> findAllByOrderByDateDesc();
}
