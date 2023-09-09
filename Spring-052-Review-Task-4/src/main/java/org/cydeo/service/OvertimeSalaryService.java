package org.cydeo.service;

import org.cydeo.repository.EmployeeRepository;
import org.cydeo.repository.HoursRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OvertimeSalaryService {

    EmployeeRepository employeeRepository;
    HoursRepository hoursRepository;

    public OvertimeSalaryService(EmployeeRepository employeeRepository, @Qualifier("Overtime") HoursRepository hoursRepository) {
        this.employeeRepository = employeeRepository;
        this.hoursRepository = hoursRepository;
    }

    public void calculateOvertimeSalary(){
        System.out.println("Salary: " + employeeRepository.getHourlyRate() * hoursRepository.getHours());
    }
}
