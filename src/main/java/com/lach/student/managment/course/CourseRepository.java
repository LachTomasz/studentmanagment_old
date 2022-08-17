package com.lach.student.managment.course;

import java.util.*;

public class CourseRepository {

    private final Map<UUID, Course> idToCourse;

    public CourseRepository(Map<UUID, Course> idToCourse) {
        this.idToCourse = idToCourse;
    }

    public Course save(Course course) {
        idToCourse.put(course.getCourseID(), course);
        return idToCourse.get(course.getCourseID());
    }

    // findById - it will be search an instance of object Course of CourseID number
    public Course find(UUID CourseID) {
        return idToCourse.get(CourseID);
    }

    // update - replace instance of object Course
    public Course update(Course course) {
        return idToCourse.put(course.getCourseID(), course);
    }

    //delete - remove instance of object Course of CourseID number
    public void delete(UUID CourseID) {
        idToCourse.remove(CourseID);
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
