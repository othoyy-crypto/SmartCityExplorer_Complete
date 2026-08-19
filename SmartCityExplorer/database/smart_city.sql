CREATE DATABASE IF NOT EXISTS smart_city;

USE smart_city;

-- =========================
-- USERS
-- =========================

CREATE TABLE IF NOT EXISTS users (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    password VARCHAR(100) NOT NULL
);


-- =========================
-- PLACES
-- =========================

CREATE TABLE IF NOT EXISTS places (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(150) NOT NULL,

    category VARCHAR(100) NOT NULL,

    location VARCHAR(200) NOT NULL,

    description VARCHAR(500)
);


INSERT INTO places (name, category, location, description) VALUES

('Lalbagh Fort', 'Historical', 'Dhaka',
 'A famous Mughal-era historical place.'),

('Ahsan Manzil', 'Historical', 'Dhaka',
 'A historic palace beside the Buriganga River.'),

('National Zoo', 'Tourist', 'Mirpur, Dhaka',
 'A popular family tourist destination.'),

('Coxs Bazar Beach', 'Beach', 'Coxs Bazar',
 'A famous sea beach and tourist destination.');


-- =========================
-- HOTELS
-- =========================

CREATE TABLE IF NOT EXISTS hotels (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(150) NOT NULL,

    location VARCHAR(200) NOT NULL,

    price DOUBLE NOT NULL,

    rating DOUBLE NOT NULL,

    description VARCHAR(500)
);


INSERT INTO hotels (name, location, price, rating, description) VALUES

('Hotel Sarina', 'Dhaka', 8500, 4.5,
 'A comfortable hotel in the heart of Dhaka.'),

('Pan Pacific Sonargaon', 'Dhaka', 12000, 4.7,
 'Premium hotel with modern facilities and excellent service.'),

('Hotel Sea Crown', 'Coxs Bazar', 5500, 4.2,
 'A comfortable hotel near the beach.'),

('The Peninsula Chittagong', 'Chittagong', 7500, 4.4,
 'A modern city hotel with comfortable rooms and facilities.');
