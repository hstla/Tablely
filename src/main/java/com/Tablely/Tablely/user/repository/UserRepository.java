package com.Tablely.Tablely.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tablely.Tablely.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
