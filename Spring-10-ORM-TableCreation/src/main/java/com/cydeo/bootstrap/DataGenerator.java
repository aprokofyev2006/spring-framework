package com.cydeo.bootstrap;

import com.cydeo.entity.Car;
import com.cydeo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {

    CarRepository carRepository;

    @Autowired
    public DataGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car c1 = new Car("DMW","X5", 2022);
        Car c2 = new Car("Honda","Civic", 2021);
        Car c3 = new Car("Toyota","Corolla", 2020);

        //save these objects to DB
        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);

        List<Car> carList = carRepository.findAll(Sort.by("year").ascending());
        System.out.println(carList);
    }
}
