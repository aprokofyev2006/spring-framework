package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.entity.Cinema;
import com.cydeo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to get cinema with a specific name
    List<Cinema> findByName(String name);

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> findTop3BySponsoredNameOrderByName(String name);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> findByLocation_Country(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> findByNameOrSponsoredName(String name, String sponsoredName);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to read the cinema name with a specific id
    @Query("SELECT c FROM Cinema c where c.id=?1")
    Cinema getById(Long id);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country
    @Query(value = "SELECT c.* FROM cinema c " +
            "JOIN location l " +
            "ON c.location_id=l.id " +
            "WHERE l.country = 'United States'",nativeQuery = true)
    List<Cinema> listByCountry(String country);

    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(value = "SELECT * FROM cinema " +
            "WHERE LOWER(name) LIKE LOWER(concat('%',:pattern,'%')) " +
            "OR sponsored_name ILIKE concat('%',:pattern,'%') ",nativeQuery = true)
    List<Cinema> listCinemasWithLikePattern(@Param("pattern") String pattern);

    //Write a native query to sort all cinemas by name
    @Query(value = "select * from cinema order by name desc", nativeQuery = true)
    List<Cinema> listCinemaSortByName();

    //Write a native query to distinct all cinemas by sponsored name
}
