package com.lach.student.managment.students;


import java.util.Objects;
import java.util.UUID;

public class Student implements User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String indexNumber;

    public Student(String firstName, String lastName, String indexNumber) {
        id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return id == student.id && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(indexNumber, student.indexNumber);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(indexNumber, student.indexNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, indexNumber);
    }

    @Override
    public int getType() {
        return 0;
    }
}
