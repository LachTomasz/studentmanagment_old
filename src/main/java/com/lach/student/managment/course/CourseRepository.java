package com.lach.student.managment.course;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository {

    private final Map<UUID, Course> idToCourse;

    public CourseRepository(Map<UUID, Course> idToCourse) {
        this.idToCourse = idToCourse;
    }

    public Course save(Course course) {
        idToCourse.put(course.getCourseID(), course);
        return idToCourse.get(course.getCourseID());
    }

    // update - replace instance of object Course
    public Course update(Course course) {
        idToCourse.replace(course.getCourseID(), course);
        return course;
    }

    //delete - remove instance of object Course of CourseID number
    public void delete(UUID CourseID) {
        idToCourse.remove(CourseID);
    }

    // findById - it will be search an instance of object Course of CourseID number
    public Course find(UUID CourseID) {
        return idToCourse.get(CourseID);
    }

    //findAll - returns list of all Course with that same name
    public List<Course> findAll(String courseName) {
        List<Course> listOfCourse = new LinkedList<>();
        for (Course c : idToCourse.values()) {
            if (c.getCourseName().equals(courseName))
                listOfCourse.add(c);
        }
        return listOfCourse;
    }

    //Overload findAll - returns list of all course in repository
    public List<Course> findAll() {
        return new ArrayList<>(idToCourse.values());
    }
}
