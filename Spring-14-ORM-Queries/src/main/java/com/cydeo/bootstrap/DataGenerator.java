package com.cydeo.bootstrap;

import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DataGenerator(RegionRepository regionRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------REGION START---------");

        System.out.println("findByCountry: "+regionRepository.findByCountry("Canada"));
        System.out.println("findDistinctByCountry: "+regionRepository.findDistinctByCountry("Canada"));
        System.out.println("findByCountryContainsIgnoreCase: "+regionRepository.findByCountryContainsIgnoreCase("United"));
        System.out.println("findByCountryContainingOrderByRegionAsc: "+regionRepository.findByCountryContainingOrderByRegionAsc("United"));
        System.out.println("findTop2ByCountry: "+regionRepository.findTop2ByCountry("Canada"));

        System.out.println("---------REGION END---------");

        System.out.println("---------DEPARTMENT START---------");

        System.out.println("findByDepartment: "+departmentRepository.findByDepartment("Toys"));
        System.out.println("findByDivisionIs: "+departmentRepository.findByDivisionIs("Health"));
        System.out.println("findByDivisionEquals: "+departmentRepository.findByDivisionEquals("Health"));
        System.out.println("findByDivisionEquals: "+departmentRepository.findDistinctTop3ByDivisionContains("Hea"));

        System.out.println("---------DEPARTMENT END---------");

        System.out.println("---------EMPLOYEE START---------");

        System.out.println("findByEmail: "+employeeRepository.findByEmail("noffincc@paypal.com"));
        System.out.println("findByFirstNameAndLastNameAndEmailNotNull: "+employeeRepository.findByFirstNameAndLastNameOrEmail("Elvira","Rubroe","btollidaycj@gov.uk"));
        //System.out.println("findByFirstNameIsNot: "+employeeRepository.findByFirstNameIsNot("Aeriell"));
        System.out.println("findByFirstNameStartingWith: "+employeeRepository.findByFirstNameStartingWith("Jes"));
        System.out.println("findBySalaryGreaterThan: "+employeeRepository.findBySalaryGreaterThan(160000));
        System.out.println("findBySalaryLessThanEqual: "+employeeRepository.findBySalaryLessThanEqual(50000));
        System.out.println("findByHireDateBetween: "+employeeRepository.findByHireDateBetween(LocalDate.of(2015,1,1),LocalDate.of(2016,1,1)));
        System.out.println("findBySalaryGreaterThanEqualOrderBySalaryDesc: "+employeeRepository.findBySalaryGreaterThanEqualOrderBySalaryDesc(160000));
        System.out.println("findDistinctTop3BySalaryLessThan: "+employeeRepository.findDistinctTop3BySalaryLessThan(100000));
        System.out.println("findByEmailIsNull: "+employeeRepository.findByEmailIsNull());

        System.out.println("---------EMPLOYEE END---------");

        System.out.println("---------EMPLOYEE JPQL START---------");

        System.out.println("getEmployeeDetail: "+employeeRepository.getEmployeeDetail());
        System.out.println("getEmployeeSalary: "+employeeRepository.getEmployeeSalary());
        System.out.println("getEmployeesContainsName: "+employeeRepository.getEmployeesContainsName());

        System.out.println("getEmployeeDetail: "+employeeRepository.getEmployeeDetail("ssymonds2@hhs.gov"));
        System.out.println("getEmployeeDetail: "+employeeRepository.getEmployeeDetail("ssymonds2@hhs.gov",95313));

        System.out.println("---------EMPLOYEE JPQL END---------");
    }
}
