package com.lach.student.managment.students;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Student student2 = new Student("John", "Muster", "12343");
        Student student3 = new Student("Jan", "Nowak", "12343");
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

        HashMap<UUID, Student> students = new HashMap<>();
        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        StudentRepository studentRepository = new StudentRepository(students);

        Student updateStudent = new Student(student1.getId(), "John", "Shmit", "12343");

        //when
        Student result = studentRepository.update(updateStudent);

        //then
        Assertions.assertEquals(updateStudent, result);
    }

    @Test
    public void shouldDeleteStudent() {
        //given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("Bartek", "Musterman","23456");

        HashMap<UUID, Student> idToStudent = new HashMap<>();
        idToStudent.put(student1.getId(), student1);
        idToStudent.put(student2.getId(), student2);
        StudentRepository studentRepository = new StudentRepository(idToStudent);

        //when
        studentRepository.delete(student1.getId());
        //then
        Assertions.assertNull(studentRepository.find(student1.getId()));
    }
}
//testy do poprawienia
//ctrl +w zaznaczam sekcje;
//        ctrl+alt+v extrachuje zmienna