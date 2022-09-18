package com.lach.student.managment.students;

import com.lach.student.managment.course.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldAddStudent() {

        //Given
        String url = "http://localhost:" + port + "/students";
        Student student = new Student("Batrek", "Lach", "GC345");

        //When
        ResponseEntity<Student> result = restTemplate.postForEntity(url, student, Student.class);

        //Them
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), student);
    }

    @Test
    void shouldUpdateStudent() {
        //Given
        String url = "http://localhost:" + port + "/students";
        Student student = new Student("Batrek", "Lach", "GC345");
        restTemplate.postForEntity(url, student, Student.class);
        Student updateStudent = new Student("Bartek", "Lach", "GC0654");

        //When
        ResponseEntity<Course> result = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(updateStudent), Course.class, updateStudent);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), updateStudent
        );
    }

    @Test
    void shouldDeleteStudent() {
        //Given

        //When

        //Them
    }

    @Test
    void shouldGetStudent() {
        //Given

        //When

        //Them
    }

    @Test
    void shouldGetAllStudents() {
        //Given

        //When

        //Them
    }

    @Test
    void shouldGetAllStudentsByName() {
        //Given

        //When

        //Them
    }
}