package com.nexus.backend.repository;

import com.nexus.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT u FROM User u " +
            "WHERE u.ministry.id = :ministryId " +
            "AND u.industry.id = :industryId " +
            "AND u.category.id = :categoryId " +
            "AND u.state.id = :stateId")
    List<User> findByPreferences(Integer ministryId, Integer industryId, Integer categoryId, Integer stateId);


}
