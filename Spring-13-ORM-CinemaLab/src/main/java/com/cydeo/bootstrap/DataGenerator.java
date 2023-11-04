package com.cydeo.bootstrap;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DataGenerator implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CinemaRepository cinemaRepository;

    @Autowired
    public DataGenerator(AccountRepository accountRepository, CinemaRepository cinemaRepository) {
        this.accountRepository = accountRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("--------AccountRepository START--------");

        System.out.println("----findByCountryOrState----");
        accountRepository.findByCountryOrState("United States","Kentucky").forEach(System.out::println);

        System.out.println("----findByAgeIsLessThanEqual----");
        accountRepository.findByAgeIsLessThanEqual(35).forEach(System.out::println);

        System.out.println("----findByRole----");
        accountRepository.findByRole(UserRole.USER).forEach(System.out::println);

        System.out.println("----findByAgeIsBetween----");
        accountRepository.findByAgeIsBetween(20,40).forEach(System.out::println);

        System.out.println("----findByAddressStartsWith----");
        accountRepository.findByAddressStartsWith("2").forEach(System.out::println);

        System.out.println("----findAllByOrderByAge----");
        accountRepository.findAllByOrderByAge().forEach(System.out::println);

        System.out.println("--------AccountRepository END--------");

        System.out.println("--------CinemaRepository START--------");

        System.out.println("----findByName----");
        cinemaRepository.findByName("Hall 1 - EMPIRE").forEach(System.out::println);

        System.out.println("--------CinemaRepository END--------");

    }
}
