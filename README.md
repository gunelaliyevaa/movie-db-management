# Movie Application

This Java-based Movie Application is a comprehensive system for managing a movie database and user watchlists. It features a GUI built with Swing, offering functionalities like movie browsing, watchlist management, and user authentication.

## Features

- **User Authentication**: Login and registration system.
- **Movie Database Management**: Add, remove, and browse movies.
- **User Watchlist**: Users can add or remove movies from their personalized watchlists.
- **Sorting and Filtering**: Movies can be sorted and filtered by various attributes.
- **Robust Exception Handling**: Handles various error scenarios gracefully.
- **File-based Persistence**: Movies and user data are stored persistently in CSV files.

## Demo

For a detailed walkthrough of the Movie Application, check out our video demonstration here: [Video Explanation](#)

## How to Run

To run the application, you need to have Java installed on your system.

1. Clone the repository to your local machine.
2. Navigate to the project directory in your terminal.
3. Compile the Java files using `javac` command.
4. Run `MovieApp` using the `java` command.

## Structure

- `MovieApp`: The main class that launches the application and sets up the primary GUI components.
- `Movie`: A class representing a movie with attributes like title, director, release year, and running time.
- `User`: Manages user data including login, registration, and watchlist functionality.
- `MovieDatabase`: Manages the collection of movies and provides functionality to add, remove, and retrieve movie details.

## GUI Components

- **LoginFrame**: A frame for user login.
- **RegistrationFrame**: A frame for new user registration.
- **MovieManagementFrame**: Main interface for browsing movies and managing watchlists.

## Usage

Upon launching the application, users can either log in or register. Once logged in, users can browse the movie database, add movies to their watchlist, and manage their watchlist.

Project Link: [https://github.com/gunelaliyevaa/movie-db-management](https://github.com/gunelaliyevaa/movie-db-management)
