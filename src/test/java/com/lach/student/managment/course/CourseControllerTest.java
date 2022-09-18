package com.lach.student.managment.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldAddCourse() {
        //Given
        String url = "http://localhost:" + port + "/courses";
        Course math = new Course("Math");

        //When
        ResponseEntity<Course> result = restTemplate.postForEntity(url, math, Course.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), math);
    }

    @Test
    void shouldUpdateCourse() {
        //Given
        String url = "http://localhost:" + port + "/courses";
        Course course = new Course("Math");
        restTemplate.postForEntity(url, course, Course.class);
        Course updateCourse = new Course(course.getCourseID(), "Algebra");

        //When
        ResponseEntity<Course> result = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(updateCourse), Course.class, updateCourse);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), updateCourse
        );
    }

    @Test
    void shouldDeleteCourse() {
        //Given
        String urlCreated = "http://localhost:" + port + "/courses";
        Course course = new Course("Math");
        restTemplate.postForEntity(urlCreated, course, Course.class);
        UUID courseID = course.getCourseID();
        String urlDelete = urlCreated + "/" + courseID;

        //When
        ResponseEntity<Course> result = restTemplate.exchange(urlDelete, HttpMethod.DELETE, new HttpEntity<>(null), Course.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertNull(result.getBody());
    }

    @Test
    void shouldGetCourse() {
        //Given
        String urlCreate = "http://localhost:" + port + "/courses";
        Course course = new Course("Math");
        restTemplate.postForEntity(urlCreate, course, Course.class);
        UUID courseID = course.getCourseID();
        String urlGet = urlCreate + "/" + courseID;

        //When
        ResponseEntity<Course> result = restTemplate.getForEntity(urlGet, Course.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), course);
    }

    @Test
    void shouldGetAllCourses() {
        //Given
        String url = "http://localhost:" + port + "/courses";
        Course course1 = new Course("Math");
        Course course2 = new Course("Physic");
        Course course3 = new Course("Algebra");
        restTemplate.postForEntity(url, course1, Course.class);
        restTemplate.postForEntity(url, course2, Course.class);
        restTemplate.postForEntity(url, course3, Course.class);
        Course[] idToCourses = new Course[]{course1,course2,course3};

        //When
        ResponseEntity<Course[]> result = restTemplate.getForEntity(url, Course[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertArrayEquals(result.getBody(), idToCourses);
    }

    @Test
    void shouldGetAllCoursesByName(){
        //Given
        String url = "http://localhost:" + port + "/courses";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", "Math").build().toUri();
        Course course1 = new Course("Math");
        Course course2 = new Course("Physic");
        Course course3 = new Course("Algebra");
        Course course4 = new Course("Math");

        restTemplate.postForEntity(url, course1, Course.class);
        restTemplate.postForEntity(url, course2, Course.class);
        restTemplate.postForEntity(url, course3, Course.class);
        restTemplate.postForEntity(url, course4, Course.class);
        Course[] courseBySameName = new Course[]{course1, course4};

        //When
        ResponseEntity<Course[]> result = restTemplate.getForEntity(uri, Course[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertArrayEquals(result.getBody(), courseBySameName);
    }
}