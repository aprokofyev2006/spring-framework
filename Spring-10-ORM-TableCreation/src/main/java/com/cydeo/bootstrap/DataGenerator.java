package com.cydeo.bootstrap;

import com.cydeo.entity.Car;
import com.cydeo.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    CarRepository carRepository;

    @Autowired
    public DataGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car c1 = new Car("DMW","X5");
        Car c2 = new Car("Honda","Civic");
        Car c3 = new Car("Toyota","Corolla");

        //save these objects to DB
        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
    }
}
