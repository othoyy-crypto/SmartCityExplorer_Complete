package com.smartcity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcity.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByLocationContainingIgnoreCase(String location);

    List<Restaurant> findByNameContainingIgnoreCase(String name);

    List<Restaurant> findByCuisineContainingIgnoreCase(String cuisine);
}