package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> findByCountryOrState (String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findByAgeIsLessThanEqual (int age);

    //Write a derived query to list all accounts with a specific role
    List<Account> findByRole (UserRole role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findByAgeIsBetween (int startAge, int endAge);

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findByAddressStartsWith (String pattern);

    //Write a derived query to sort the list of accounts with age
    List<Account> findAllByOrderByAge ();

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("SELECT a FROM Account a")
    List<Account> getAllAccounts ();

    //Write a JPQL query to list all admin accounts
    @Query("SELECT a FROM Account a WHERE a.role=?1")
    List<Account> getAllAdminAccounts (UserRole role);

    //Write a JPQL query to sort all accounts with age
    @Query("SELECT a FROM Account a ORDER BY a.age")
    List<Account> getAllAccountsSortedByAge ();


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "SELECT * FROM account_details WHERE age < :age",nativeQuery = true)
    List<Account> listAccountsWithAgeLowerThan(int age);

    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state city
    @Query(value = "SELECT * FROM account_details " +
            "WHERE LOWER(name) LIKE LOWER(concat('%',:pattern,'%')) " +
            "OR address ILIKE concat('%',:pattern,'%') " +
            "OR country ILIKE concat('%',:pattern,'%') " +
            "OR state ILIKE concat('%',:pattern,'%') " +
            "OR city ILIKE concat('%',:pattern,'%')",nativeQuery = true)
    List<Account> listAccountsWithLikePattern(@Param("pattern") String pattern);


}
