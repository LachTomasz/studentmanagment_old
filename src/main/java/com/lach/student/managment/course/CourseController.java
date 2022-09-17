package com.lach.student.managment.course;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    Map<UUID, Course> idToCourse = new HashMap<>();

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){
        idToCourse.put(course.getCourseID(), course);
        return course;
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        idToCourse.replace(course.getCourseID(), course);
        return course;
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable("id") String id ){
        UUID courseId = UUID.fromString(id);
        idToCourse.remove(courseId);
    }

    @GetMapping("courses/{id}")
    public Course getCourse(@PathVariable("id") String id){
        UUID courseId = UUID.fromString(id);
        return idToCourse.get(courseId);
    }

    @GetMapping("/courses")
    public Collection getAllCourses(@RequestParam(value = "name", required = false) String name){
        if(name != null) return idToCourse.values().stream()
                .filter((course) -> course.getCourseName().equals(name))
                .collect(Collectors.toList());
        else return idToCourse.values();
    }
}
