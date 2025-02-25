package com.Tablely.Tablely.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tablely.Tablely.restaurant.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Long, Restaurant> {

}
