package com.smartcity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.smartcity.model.Hotel;
import com.smartcity.repository.HotelRepository;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Get all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get hotel by ID
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    // Search hotels by name
    @GetMapping("/search")
    public List<Hotel> searchByName(@RequestParam String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name);
    }

    // Search hotels by location
    @GetMapping("/location")
    public List<Hotel> searchByLocation(@RequestParam String location) {
        return hotelRepository.findByLocationContainingIgnoreCase(location);
    }

    // Add hotel
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Update hotel
    @PutMapping("/{id}")
    public Hotel updateHotel(
            @PathVariable Long id,
            @RequestBody Hotel hotel) {

        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        existingHotel.setPrice(hotel.getPrice());
        existingHotel.setRating(hotel.getRating());
        existingHotel.setDescription(hotel.getDescription());

        return hotelRepository.save(existingHotel);
    }

    // Delete hotel
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Long id) {

        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel not found");
        }

        hotelRepository.deleteById(id);

        return "Hotel deleted successfully";
    }
}