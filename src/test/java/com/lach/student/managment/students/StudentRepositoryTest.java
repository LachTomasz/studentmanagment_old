package com.lach.student.managment.students;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class StudentRepositoryTest {

    @Test
    public void
    shuoldSaveStudent() {

        //Given
        StudentRepository studentRepository = new StudentRepository(new HashMap<>());
        Student student = new Student("John", "Kowalski", "12343");

        //When
        var result = studentRepository.save(student);

        //Then
        Assertions.assertEquals(student, result);
    }

    @Test
    public void shouldFindStudent() {

        //given
        Student student = new Student("John", "Kowalski", "12343");
        Map<UUID, Student> students = Map.of(student.getId(), student);
        StudentRepository studentRepository = new StudentRepository(students);

        //when
        var result = studentRepository.find(student.getId());

        //then
        Assertions.assertEquals(student, result);
    }

    @Test
    public void shouldFindAllStudentsWithParam() {
        //given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("John", "Kowalski", "12343");
        Student student3 = new Student("Jan", "Kowalski", "12343");
        Map<UUID, Student> students = Map.of(student1.getId(), student1,
                                                student2.getId(), student2,
                                                student3.getId(), student3);
        StudentRepository studentRepository = new StudentRepository(students);

        //when
        var result = studentRepository.findAll("John");

        //then
        Assertions.assertEquals(List.of(student1, student2), result);
    }

    @Test
    public void shouldFindAllStudents(){
        //given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("John", "Kowalski", "12343");
        Student student3 = new Student("Jan", "Kowalski", "12343");
        Map<UUID, Student> students = Map.of(student1.getId(), student1,
                                                student2.getId(), student2,
                                                student3.getId(), student3);
        StudentRepository studentRepository = new StudentRepository(students);

        //when
        var result = studentRepository.findAll();

        //then
        Assertions.assertEquals(List.of(student1, student2, student3), result);
    }


    @Test
    public void shouldUpdateStudent() {

        //given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("Jan","Bykowski","12345");

        StudentRepository studentRepository = new StudentRepository(new HashMap<>());
        studentRepository.save(student1);
        studentRepository.save(student2);
        student1 = new Student("Jan","Bykowski","12345");

        //when
        Student result = studentRepository.update(student1);

        //then
        Assertions.assertEquals(student1, result);
    }

    @Test
    public void shouldDeleteStudent() {
        //given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("Bartek", "Musterman","23456");


//        Map<UUID, Student> students = Map.of(student1.getId(), student1,
//                                                student2.getId(), student2);
//        StudentRepository studentRepository = new StudentRepository(students);
//        StudentRepository studentRepository1 = MutableMap

        StudentRepository studentRepository = new StudentRepository(new HashMap<>());
        studentRepository.save(student1);
        studentRepository.save(student2);

        //when
        studentRepository.delete(student1.getId());
        //then
        Assertions.assertNull(studentRepository.find(student1.getId()));
    }
}
//testy do poprawienia
//ctrl +w zaznaczam sekcje;
//        ctrl+alt+v extrachuje zmienna