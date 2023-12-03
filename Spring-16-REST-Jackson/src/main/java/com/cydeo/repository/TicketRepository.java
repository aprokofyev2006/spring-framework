package com.cydeo.repository;

import com.cydeo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    Integer countAllByUserAccountId(Long userId);

    //Write a derived query to list all tickets by specific email
    List<Ticket> findAllByUserAccount_Email(String email);

    //Write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinema_Movie_Name(String name);

    //Write a derived query to list all tickets between a range of dates
    List<Ticket> findAllByDateTimeBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user
    @Query("select t from Ticket t where t.userAccount.id = ?1")
    List<Ticket> fetchAllTicketsByUserAccount(Long UserId);

    //Write a JPQL query that returns all tickets between a range of dates
    @Query("select t from Ticket t where t.dateTime between ?1 and ?2")
    List<Ticket> fetchAllTicketsBetweenRangeOfDateTimes(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(value = "select count(*) from ticket where user_account_id = ?1",nativeQuery = true)
    Integer countAllTicketsByUserAccount(Long UserId);

    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value = "select count(*) from ticket wher user_account_id = ?1 and date_time between ?2 and ?3",nativeQuery = true)
    Integer countNumberOfTicketsForRange(Long UserId, LocalDateTime localDateTime1, LocalDateTime localDateTime2);

    //Write a native query to distinct all tickets by movie name
    @Query(value = "select distinct(m.name) from ticket t join movie_cinema mc on t.movie_cinema_id = mc.id " +
            "join movie m on mc.movie_id = m.id",nativeQuery = true)
    List<String> retrieveAllDistinctMovieName();

    //Write a native query to find all tickets by user email
    @Query(value = "select * from ticket t join user_account ua on t.user_account_id = ua.id " +
            "where ua.email = ?1",nativeQuery = true)
    List<Ticket> findAllByUserEmail(String email);

    //Write a native query that returns all tickets
    @Query(value = "select * from ticket",nativeQuery = true)
    List<Ticket> retrieveAll();

    //Write a native query to list all tickets where a specific value should be containable in the username or name or movie name

}
