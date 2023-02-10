package com.earworm.backendearworm;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

    // @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findUserByEmail(String email);

    // List<User> findAllByZipcode(int zipCode);

    // @Query("SELECT s FROM User s WHERE s.username = ?1")
    Optional<User> existsByUsername(String username);

    @Transactional
    @Modifying
    // @Query("UPDATE user a " + "Set a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

}
