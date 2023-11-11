package com.cydeo.bootstrap;

import com.cydeo.enums.UserRole;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.CinemaRepository;
import com.cydeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DataGenerator implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CinemaRepository cinemaRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataGenerator(AccountRepository accountRepository, CinemaRepository cinemaRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.cinemaRepository = cinemaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("--------AccountRepository START--------");

//        System.out.println("----findByCountryOrState----");
//        accountRepository.findByCountryOrState("United States","Kentucky").forEach(System.out::println);
//
//        System.out.println("----findByAgeIsLessThanEqual----");
//        accountRepository.findByAgeIsLessThanEqual(35).forEach(System.out::println);
//
//        System.out.println("----findByRole----");
//        accountRepository.findByRole(UserRole.USER).forEach(System.out::println);
//
//        System.out.println("----findByAgeIsBetween----");
//        accountRepository.findByAgeIsBetween(20,40).forEach(System.out::println);
//
//        System.out.println("----findByAddressStartsWith----");
//        accountRepository.findByAddressStartsWith("2").forEach(System.out::println);
//
//        System.out.println("----findAllByOrderByAge----");
//        accountRepository.findAllByOrderByAge().forEach(System.out::println);

//        System.out.println("----getAllAccounts----");
//        accountRepository.getAllAccounts().forEach(System.out::println);

//        System.out.println("----getAllAccountsSortedByAge----");
//        accountRepository.getAllAccountsSortedByAge().forEach(System.out::println);

//        System.out.println("----getAllAdminAccounts----");
//        accountRepository.getAllAdminAccounts(UserRole.ADMIN).forEach(System.out::println);

//        System.out.println("----getAllAdminAccounts----");
//        accountRepository.listAccountsWithAgeLowerThan(40).forEach(System.out::println);

//        System.out.println("----getAllAdminAccounts----");
//        accountRepository.listAccountsWithLikePattern("d").forEach(System.out::println);

        System.out.println("--------AccountRepository END--------");

        System.out.println("--------CinemaRepository START--------");

//        System.out.println("----findByName----");
//        cinemaRepository.findByName("Hall 1 - EMPIRE").forEach(System.out::println);

//        System.out.println("----findTop3BySponsoredNameOrderByName----");
//        cinemaRepository.findTop3BySponsoredNameOrderByName("Kodak").forEach(System.out::println);

//        System.out.println("----findByLocation_Country----");
//        cinemaRepository.findByLocation_Country("United States").forEach(System.out::println);

//        System.out.println("----findByNameOrSponsoredName----");
//        cinemaRepository.findByNameOrSponsoredName("Hall 2 - EMPIRE", "Kodak").forEach(System.out::println);

//        System.out.println("----getById----");
//        System.out.println(cinemaRepository.getById(10L));

//        System.out.println("----listByCountry----");
//        cinemaRepository.listByCountry("United States").forEach(System.out::println);

//        System.out.println("----listCinemasWithLikePattern----");
//        cinemaRepository.listCinemasWithLikePattern("et").forEach(System.out::println);

        System.out.println("----listCinemaSortByName----");
        cinemaRepository.listCinemaSortByName().forEach(System.out::println);

        System.out.println("--------CinemaRepository END--------");

        System.out.println("--------UserRepository START--------");

//        System.out.println("----findByName----");
//        userRepository.findByEmail("johnnie@email.com").forEach(System.out::println);

        System.out.println("--------UserRepository END--------");

    }
}
