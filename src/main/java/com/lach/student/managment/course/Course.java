package com.lach.student.managment.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Course {

    private final UUID courseID;
    private final String courseName;

    public Course(@JsonProperty String courseName) {
        this(UUID.randomUUID(), courseName);
    }
    public Course(@JsonProperty UUID courseID, @JsonProperty String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }
    public UUID getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                '}';
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
