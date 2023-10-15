package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentFirstName", length = 100)
    private String firstName;

    @Column(name = "studentLastName", length = 100)
    private String lastName;
    private String email;

    @Transient //field with this annotation will not be created in table
    private String city;

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(columnDefinition = "TIME")
    private LocalTime birthTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate birthDateTime;

    @Enumerated(EnumType.STRING)
    private Gender gender;



}
