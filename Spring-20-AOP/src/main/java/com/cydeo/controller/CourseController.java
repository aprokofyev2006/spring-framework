package com.cydeo.controller;

import com.cydeo.annotation.Loggable;
import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // @Controller + @ResponseBody
@RequestMapping("/courses/api/v1")
public class CourseController {

    private final CourseService courseService;

    //Logger logger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        //logger.info("Before -> controller: {} - Method: {}", "CourseController", "getCourses()");

        List<CourseDTO> list = courseService.getCourses();

        //logger.info("After -> controller: {} - Method: {} - Output: {}", "CourseController", "getCourses()", list.toString());
        return list;
    }

    @Loggable
    @GetMapping("{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @GetMapping("category/{name}")
    public List<CourseDTO> getCoursesByCategory(@PathVariable("name") String category){
        return  courseService.getCoursesByCategory(category);
    }

    @Loggable
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDto){
        return courseService.createCourse(courseDto);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseDTO courseDto){
        courseService.updateCourse(courseId, courseDto);
    }

    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping()
    public void deleteCourses(){
        courseService.deleteCourses();
    }

}
