package org.cydeo.service;

import lombok.AllArgsConstructor;
import org.cydeo.repository.EmployeeRepository;
import org.cydeo.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SalaryService {

    EmployeeRepository employeeRepository;
    HoursRepository hoursRepository;

    public SalaryService(EmployeeRepository employeeRepository,@Qualifier("Regular") HoursRepository hoursRepository) {
        this.employeeRepository = employeeRepository;
        this.hoursRepository = hoursRepository;
    }

    public void calculateRegularSalary(){
        System.out.println("Salary: " + employeeRepository.getHourlyRate() * hoursRepository.getHours());
    }
}
