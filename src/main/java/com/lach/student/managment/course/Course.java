package com.lach.student.managment.course;

import java.util.Objects;
import java.util.UUID;

public class Course {

    private final UUID courseID;
    private final String courseName;

    public Course(String courseName) {
        courseID = UUID.randomUUID();
        this.courseName = courseName;
    }

    public UUID getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseID, course.courseID) && Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName);
    }

}
