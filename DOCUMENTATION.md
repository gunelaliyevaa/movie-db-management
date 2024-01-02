# Movie Application Documentation

## Overview

The Movie Application is a Java-based desktop solution designed for efficient management and retrieval of movie-related data. It offers comprehensive functionality for movie database manipulation and user-specific watchlist management. Built utilizing Java Swing, the application provides an intuitive graphical user interface for enhanced user interaction and experience.

## Key Components

### Movie Class

- **Purpose**: Represents a film entity encompassing essential cinematic attributes.
- **Attributes**:
  - `title` (String): The title of the movie.
  - `director` (String): The director of the movie.
  - `releaseYear` (int): The year in which the movie was released.
  - `runningTime` (int): The running time of the movie in minutes.
- **Key Methods**:
  - `getTitle()`: Retrieves the movie's title.
  - `getDirector()`: Retrieves the movie's director.
  - `getReleaseYear()`: Retrieves the year of release.
  - `getRunningTime()`: Retrieves the running time of the movie.
  - `toString()`: Provides a formatted string representation of the movie details.

### User Class

- **Purpose**: Manages user-specific information, including authentication and watchlist operations.
- **Attributes**:
  - `username` (String): The user's unique identifier.
  - `password` (String): The user's password for authentication.
  - `watchlist` (List<Movie>): A list representing the user's personal watchlist.
- **Key Methods**:
  - `login(username, password)`: Authenticates user credentials.
  - `register(username, password)`: Registers a new user.
  - `addToWatchlist(movie)`: Adds a movie to the user's watchlist.
  - `removeFromWatchlist(movie)`: Removes a movie from the user's watchlist.

### MovieDatabase Class

- **Purpose**: Facilitates the management of the movie repository.
- **Attributes**:
  - `movies` (List<Movie>): A list of movies in the database.
- **Key Methods**:
  - `addMovie(movie)`: Adds a new movie to the database.
  - `removeMovie(title)`: Removes a movie from the database.
  - `getMovies()`: Retrieves all movies from the database.

### Graphical User Interface (Swing)

- **Components**:
  - `LoginFrame`: Manages user login process.
  - `RegistrationFrame`: Handles new user registrations.
  - `MovieManagementFrame`: Primary interface for user interaction with the movie database and watchlist.

## Functionalities

### Authentication System

- Robust user authentication mechanism, including secure login and registration functionalities.

### Movie Database Operations

- Comprehensive capabilities to add, view, and delete movies from the database.

### User Watchlist Management

- Personalized watchlist operations allowing users to curate their own list of favorite movies.

### Advanced Data Manipulation

- Features for sorting and filtering movies based on various criteria.

### Data Persistence

- Utilizes file-based storage (CSV format) to maintain data persistence across user sessions.

## Technical Details

- **Development Language**: Java
- **User Interface Framework**: Java Swing
- **Data Storage Format**: Comma-Separated Values (CSV)

## System Requirements

- **Runtime Environment**: Java Runtime Environment (JRE)

This documentation is crafted to provide a comprehensive understanding of the Movie Application's architecture, components, and functionalities, adhering to professional Java documentation standards.
