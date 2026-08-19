package com.smartcity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.smartcity.model.Restaurant;
import com.smartcity.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Get all restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Get restaurant by ID
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Search by name
    @GetMapping("/search")
    public List<Restaurant> searchByName(@RequestParam String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    // Search by location
    @GetMapping("/location")
    public List<Restaurant> searchByLocation(@RequestParam String location) {
        return restaurantRepository.findByLocationContainingIgnoreCase(location);
    }

    // Search by cuisine
    @GetMapping("/cuisine")
    public List<Restaurant> searchByCuisine(@RequestParam String cuisine) {
        return restaurantRepository.findByCuisineContainingIgnoreCase(cuisine);
    }

    // Add restaurant
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Update restaurant
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setLocation(restaurant.getLocation());
        existingRestaurant.setCuisine(restaurant.getCuisine());
        existingRestaurant.setRating(restaurant.getRating());
        existingRestaurant.setDescription(restaurant.getDescription());

        return restaurantRepository.save(existingRestaurant);
    }

    // Delete restaurant
    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {

        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }

        restaurantRepository.deleteById(id);

        return "Restaurant deleted successfully";
    }
}