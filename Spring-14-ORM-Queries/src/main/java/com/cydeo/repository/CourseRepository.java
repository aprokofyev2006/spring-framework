package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //find all courses by category
    List<Course> findByCategory(String category);

    //find all courses by category and order entities by name
    List<Course> findByCategoryOrderByName(String category);

    //check if a course with the provided name exist. Return true if course exists, false otherwise.
    boolean existsByName(String name);

    //returns the count of courses for the provided category
    int countByCategory(String category);

    //find all courses that start with the provided course name
    List<Course> findByNameStartsWith(String name);

    //find all courses by category and returns a stream
    Stream<Course> streamByCategory (String category);

    @Query("select c from Course c where c.category = :category and c.rating > :rating")
    List<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category, @Param("rating") int rating);

}
