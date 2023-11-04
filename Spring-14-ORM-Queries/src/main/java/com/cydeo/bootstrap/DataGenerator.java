package com.cydeo.bootstrap;

import com.cydeo.repository.CourseRepository;
import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Transactional
public class DataGenerator implements CommandLineRunner {

    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public DataGenerator(RegionRepository regionRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, CourseRepository courseRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.courseRepository = courseRepository;
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

        System.out.println("----getDepartmentsForDivisions----");
        departmentRepository.getDepartmentsForDivisions(Arrays.asList("Home","Electronics")).forEach(System.out::println);

        System.out.println("----retrieveDepartmentByDivision----");
        departmentRepository.retrieveDepartmentByDivision("Electronics").forEach(System.out::println);

        System.out.println("----retrieveDepartmentByDivisionContain----");
        departmentRepository.retrieveDepartmentByDivisionContain("tronic").forEach(System.out::println);

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

        //System.out.println("----getEmployeeSalaryNotEqual----");
        //employeeRepository.getEmployeeSalaryNotEqual(160000).forEach(System.out::println);

        System.out.println("----getEmployeeFirstNameLike----");
        employeeRepository.getEmployeeFirstNameLike("Bill%").forEach(System.out::println);

        System.out.println("----getEmployeeSalaryLessThan----");
        employeeRepository.getEmployeeSalaryLessThan(21000).forEach(System.out::println);

        System.out.println("----getEmployeeSalaryGraterThan----");
        employeeRepository.getEmployeeSalaryGraterThan(165000).forEach(System.out::println);

        System.out.println("----getEmployeeHireDateBetween----");
        employeeRepository.getEmployeeHireDateBetween(LocalDate.of(2014,1,1), LocalDate.of(2014,2,1)).forEach(System.out::println);

        System.out.println("----getEmployeeSalaryBetween----");
        employeeRepository.getEmployeeSalaryBetween(165000, 166000).forEach(System.out::println);

        //System.out.println("----getEmployeeEmailIsNull----");
        //employeeRepository.getEmployeeEmailIsNull().forEach(System.out::println);

        //System.out.println("----getEmployeeEmailIsNotNull----");
        //employeeRepository.getEmployeeEmailIsNotNull().forEach(System.out::println);

        System.out.println("----getFiveEmployeeSortedByNameInAsc----");
        employeeRepository.getFiveEmployeeSortedByNameInAsc().forEach(System.out::println);

        System.out.println("----getFiveEmployeeSortedByNameInDesc----");
        employeeRepository.getFiveEmployeeSortedByNameInDesc().forEach(System.out::println);

        System.out.println("----updateEmployeeJPQL----");
        employeeRepository.updateEmployeeJPQL(1);

        System.out.println("---------EMPLOYEE JPQL END---------");

        System.out.println("---------EMPLOYEE NATIVE QUERY START---------");
        System.out.println("----readEmployeeDetailsBySalary-----");
        employeeRepository.readEmployeeDetailsBySalary(56752).forEach(System.out::println);

        System.out.println("----getEmployeeBySalary-----");
        employeeRepository.getEmployeeBySalary(95313).forEach(System.out::println);

        System.out.println("----updateEmployeeNativeQuery-----");
        employeeRepository.updateEmployeeNativeQuery(2,"admin_edit@yopmail.com");
        System.out.println("---------EMPLOYEE NATIVE QUERY END---------");

        System.out.println("---------COURSES START---------");

        courseRepository.findByCategory("Spring").forEach(System.out::println);
        System.out.println("----findByCategoryOrderByName-----");
        courseRepository.findByCategoryOrderByName("Spring").forEach(System.out::println);
        System.out.println("---------");
        System.out.println("existsByName: " + courseRepository.existsByName("Rapid Spring Boot Application Development"));
        System.out.println("existsByName: " + courseRepository.existsByName("Rapid Spring Boot Application"));
        System.out.println("countByCategory: " + courseRepository.countByCategory("Spring"));
        System.out.println("----findByNameStartsWith----");
        courseRepository.findByNameStartsWith("Getting Started").forEach(System.out::println);

        System.out.println("----streamByCategory----");
        courseRepository.streamByCategory("Spring").forEach(System.out::println);

        System.out.println("----findAllByCategoryAndRatingGreaterThan----");
        courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 4).forEach(System.out::println);

        System.out.println("---------COURSES END---------");
    }
}
