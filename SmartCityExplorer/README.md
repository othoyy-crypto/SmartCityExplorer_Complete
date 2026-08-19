# Smart City Explorer
JavaFX Frontend + Spring Boot Backend + MySQL Database

## Requirements
- JDK 17
- MySQL Server
- VS Code or IntelliJ IDEA
- Maven (optional if you use the Maven wrapper; this starter uses normal Maven)

## Project structure
SmartCityExplorer/
  backend/   -> Spring Boot REST API
  frontend/  -> JavaFX desktop application
  database/  -> MySQL setup script

## STEP 1: Create the database
1. Open MySQL Workbench.
2. Open `database/smart_city.sql`.
3. Run the whole script.
4. It creates database `smart_city` and some sample places.

## STEP 2: Set your MySQL password
Open:
`backend/src/main/resources/application.properties`

Change:
`spring.datasource.password=YOUR_MYSQL_PASSWORD`

If your MySQL username is not `root`, change the username too.

## STEP 3: Start the backend
Open a terminal in the `backend` folder.

Run:
`mvn spring-boot:run`

When it starts successfully, the backend runs at:
`http://localhost:8080`

Test in a browser:
`http://localhost:8080/api/places`

You should see JSON containing sample places.

## STEP 4: Start the JavaFX frontend
Open another terminal in the `frontend` folder.

Run:
`mvn clean javafx:run`

The Smart City Explorer desktop window should open.

IMPORTANT:
The backend terminal must stay running while you use the frontend.

## If Maven is not recognized
Install Maven and add it to PATH, then reopen VS Code.
Or use IntelliJ IDEA's Maven support.

## What is connected?
JavaFX frontend
   -> HTTP REST API
Spring Boot backend
   -> Spring Data JPA
MySQL database

## Current features
- Register
- Login
- View places
- Search places
- Add a place
- MySQL storage

This is a starter project. More features such as hotels, restaurants, favorites, reviews and admin can be added after this version runs.
