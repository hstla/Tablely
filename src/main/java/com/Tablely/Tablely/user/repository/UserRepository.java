package com.Tablely.Tablely.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tablely.Tablely.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
