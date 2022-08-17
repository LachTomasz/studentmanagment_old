package com.lach.student.managment.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {

    @Test
    void shouldSaveCourse() {
        //given
        CourseRepository courseRepository = new CourseRepository(new HashMap<>());
        Course course = new Course("Math");

        //when
        var result = courseRepository.save(course);

        //then
        assertEquals(course, result);
    }

    @Test
    void shouldFindCourse() {
        //given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Map<UUID, Course> courses = Map.of(course1.getCourseID(),course1,
                                            course2.getCourseID(), course2);
        CourseRepository courseRepository = new CourseRepository(courses);

        //when
        var result = courseRepository.find(course1.getCourseID());

        //then
        assertEquals(course1, result);
    }

    @Test
    void shouldUpdateCourse() {
        //given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");

        HashMap<UUID, Course> courses = new HashMap<>();
        courses.put(course1.getCourseID(), course1);
        courses.put(course2.getCourseID(), course2);
        CourseRepository courseRepository = new CourseRepository(courses);

        course1 = new Course("Algebra");

        //when
        Course result = courseRepository.update(course1);

        //then
        assertEquals(course1, result);
    }

    @Test
    void shouldDeleteCourse() {
        //given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");

        HashMap<UUID,Course> courses = new HashMap<>();
        courses.put(course1.getCourseID(), course1);
        courses.put(course2.getCourseID(), course2);

        CourseRepository courseRepository = new CourseRepository(courses);

        //when
        courseRepository.delete(course1.getCourseID());

        //then
        Assertions.assertNull(courseRepository.find(course1.getCourseID()));
    }

    @Test
    void shouldFindAllCoursesWithParam() {
        //given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Math");
        Map<UUID, Course> courses = Map.of(course1.getCourseID(), course1,
                                            course2.getCourseID(), course2,
                                            course3.getCourseID(), course3);
        CourseRepository courseRepository = new CourseRepository(courses);

        //when
        var result = courseRepository.findAll("Math");

        //then
        Assertions.assertEquals(List.of(course1,course3), result);
    }

    @Test
    void shouldFindAllCourses() {
        //given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Algorithms");
        HashMap<UUID, Course>  courses = new HashMap<>();
        courses.put(course1.getCourseID(), course1);
        courses.put(course2.getCourseID(), course2);
        courses.put(course3.getCourseID(), course3);
//        Map<UUID, Course> courses = Map.of(course1.getCourseID(), course1,
//                                            course2.getCourseID(), course2,
//                                            course3.getCourseID(), course3);
        CourseRepository courseRepository = new CourseRepository(courses);

        //when
        var result = courseRepository.findAll();

        //then
//        Assertions.assertEquals(List.of(course1, course2, course3), result);
        Assertions.assertEquals(courses, result);
    }
}