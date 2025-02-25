package com.Tablely.Tablely.restaurantOwner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tablely.Tablely.restaurantOwner.domain.RestaurantOwner;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {

}
