package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    Optional<Movie> getAllByName(String name);

    //Write a derived query to list all movies between a range of prices
    List<Movie> getAllByPriceBetween(BigDecimal priceStart, BigDecimal priceEnd);

    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> getAllByDurationIn(List<Integer> durations);

    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> findAllByReleaseDateAfter(LocalDate releaseDate);

    //Write a derived query to list all movies with a specific state and type
    List<Movie> getAllByStateAndType(MovieState state, MovieType type);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("SELECT m from Movie m WHERE m.price BETWEEN ?1 and ?2")
    List<Movie> getByPriceBetween(BigDecimal priceStart, BigDecimal priceEnd);

    //Write a JPQL query that returns all movie names
    @Query("select m.name from Movie m")
    List<String> fetchAllMovieNames();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name
    @Query(value = "select * from movie where name = ?1",nativeQuery = true)
    Optional<Movie> retrieveByName(String name);

    //Write a native query that return the list of movies in a specific range of prices
    @Query(value = "SELECT * FROM movie WHERE price BETWEEN ?1 AND ?2",nativeQuery = true)
    List<Movie> retrieveByPriceRange(BigDecimal price1,BigDecimal price2);

    //Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "SELECT * FROM movie WHERE duration IN ?1",nativeQuery = true)
    List<Movie> retrieveByDurationInRange(List<Integer> durations);

    //Write a native query to list the top 5 most expensive movies
    @Query(value = "SELECT * FROM movie ORDER BY price DESC LIMIT 5 ",nativeQuery = true)
    List<Movie> top5ExpensiveMovies();
}
