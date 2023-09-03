package org.cydeo.repository;

import lombok.AllArgsConstructor;
import org.cydeo.model.Employee;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBEmployeeRepository implements EmployeeRepository{

    @Override
    public int getHourlyRate() {
        Employee employee = new Employee("Jhon","Sales",25);
        return employee.getHourlyRate();
    }
}
