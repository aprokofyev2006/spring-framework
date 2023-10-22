package com.cydeo.bootstrap;

import com.cydeo.entity.Department;
import com.cydeo.entity.Employee;
import com.cydeo.entity.Region;
import com.cydeo.enums.Gender;
import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;

    @Autowired
    public DataGenerator(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();

        Employee e1 = new Employee("Bill","Johnson","bill@yopmail.com", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(80000.03));
        Employee e2 = new Employee("Sam","Johnson","sam@yopmail.com", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(90000.03));
        Employee e3 = new Employee("Dean","Johnson","dean@yopmail.com", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(100000.03));

        Department d1 = new Department("Dep one","division one");
        Department d2 = new Department("Dep two","division two");
        Department d3 = new Department("Dep three","division three");

        Region r1 = new Region("Region 1","USA");
        Region r2 = new Region("Region 2","Germany");
        Region r3 = new Region("Region 3","China");

        e1.setDepartment(d1);
        e2.setDepartment(d2);
        e3.setDepartment(d3);

        e1.setRegion(r1);
        e2.setRegion(r2);
        e3.setRegion(r3);

        departmentList.addAll(Arrays.asList(d1,d2,d3));
        employeeList.addAll(Arrays.asList(e1,e2,e3));

        employeeRepository.saveAll(employeeList);
        //departmentRepository.saveAll(departmentList);


    }
}
