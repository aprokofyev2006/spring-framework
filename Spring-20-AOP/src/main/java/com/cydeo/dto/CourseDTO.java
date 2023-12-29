package com.cydeo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"},ignoreUnknown = true)
@ToString
public class CourseDTO {
    //@JsonIgnore
    private Long id;
    private String name;
    private String category;
    private int rating;
    private String description;

}
