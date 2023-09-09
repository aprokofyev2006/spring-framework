package org.cydeo.service;

import org.cydeo.repository.EmployeeRepository;
import org.cydeo.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OvertimeSalaryService {

    EmployeeRepository employeeRepository;
    HoursRepository hoursRepository;
    HoursRepository hoursRepository2;

    public OvertimeSalaryService(EmployeeRepository employeeRepository, @Qualifier("Overtime") HoursRepository hoursRepository, @Qualifier("Regular") HoursRepository hoursRepository2) {
        this.employeeRepository = employeeRepository;
        this.hoursRepository = hoursRepository;
        this.hoursRepository2 = hoursRepository2;
    }

    public void calculateOvertimeSalary(){
        System.out.println("Regular Salary: " + employeeRepository.getHourlyRate() * hoursRepository2.getHours());
        System.out.println("Salary: " + employeeRepository.getHourlyRate() * hoursRepository.getHours());
    }
}
