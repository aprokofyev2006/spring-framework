package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //Display all employees with email address 'erubroec9@instagram.com'

    List<Employee> findByEmail(String email);

    //Display all employees with first name '' and last name '', also show all employees with an email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    //Display all employees that first name is not ''
    List<Employee> findByFirstNameIsNot(String firstName);

    //Display all employees where last name starts with ''
    List<Employee> findByFirstNameStartingWith(String pattern);

    //Display all employees with salaries higher than ''
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employees with salaries less than ''
    List<Employee> findBySalaryLessThanEqual(Integer salary);

    //Display all employees that has been hired between '' and '' date
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employees where salaries greater and equal to '' in desc order
    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //Display top unique 3 employees that is making less than ''
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();


    //JPQL queries

    @Query("SELECT e FROM Employee e WHERE e.email = 'ssymonds2@hhs.gov'")
    Employee getEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'ssymonds2@hhs.gov'")
    Integer getEmployeeSalary();

    @Query("SELECT e FROM Employee e WHERE e.firstName like 'Jes%'")
    List<Employee> getEmployeesContainsName();

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> getEmployeeDetail(String email);

    @Query("SELECT e FROM Employee e WHERE e.email = ?1 and e.salary = ?2")
    Employee getEmployeeDetail(String email, int salary);

    //Not Equal
    @Query("SELECT e FROM Employee e WHERE e.salary <> ?1")
    List<Employee> getEmployeeSalaryNotEqual(int Salary);

    //like/contains/startrs with/ends with
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee> getEmployeeFirstNameLike(String pattern);

    //less than
    @Query("SELECT e FROM Employee e WHERE e.salary < ?1")
    List<Employee> getEmployeeSalaryLessThan(int salary);

    //grater than
    @Query("SELECT e FROM Employee e WHERE e.salary > ?1")
    List<Employee> getEmployeeSalaryGraterThan(int salary);

    //between
    @Query("SELECT e FROM Employee e WHERE e.hireDate between ?1 and ?2")
    List<Employee> getEmployeeHireDateBetween(LocalDate startDate, LocalDate endDate);

    //between
    @Query("SELECT e FROM Employee e WHERE e.salary between ?1 and ?2")
    List<Employee> getEmployeeSalaryBetween(int salary1, int salary2);

    //Null
    @Query("SELECT e FROM Employee e WHERE e.email IS NULL")
    List<Employee> getEmployeeEmailIsNull();

    //Not Null
    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL")
    List<Employee> getEmployeeEmailIsNotNull();

    //Sorting in asc order
    @Query("SELECT e FROM Employee e ORDER BY e.firstName LIMIT 5")
    List<Employee> getFiveEmployeeSortedByNameInAsc();

    //Sorting in desc order
    @Query("SELECT e FROM Employee e ORDER BY e.firstName DESC LIMIT 5")
    List<Employee> getFiveEmployeeSortedByNameInDesc();


    //Native query
    @Query(value = "SELECT * FROM employees WHERE salary = ?1", nativeQuery = true)
    List<Employee> readEmployeeDetailsBySalary(int salary);

    @Query(value = "SELECT * FROM employees WHERE salary = :salary", nativeQuery = true)
    List<Employee> getEmployeeBySalary(@Param("salary") int salary);

    @Transactional
    @Modifying
    @Query(value = "update employees set email =:email where id = :id", nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") int id, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update Employee e set e.email = 'admin@yopmaiol.com' where e.id = :id")
    void updateEmployeeJPQL(@Param("id") int id);

}
