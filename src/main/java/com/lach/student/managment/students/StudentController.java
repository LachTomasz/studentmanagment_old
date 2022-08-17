package com.lach.student.managment.students;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    Map<UUID, Student> idToStudent = new HashMap<>();

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        idToStudent.put(student.getId(),student);
        return student;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student studnet){
        idToStudent.replace(studnet.getId(), studnet);
        return studnet;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id")UUID id){
        idToStudent.remove(id);
    }

    @GetMapping("students/{id}")
    public Student getStudent(@PathVariable("id") UUID id){
        return idToStudent.get(id);
    }

    @GetMapping("/students")
    public Collection<Student> getAllStudents(@RequestParam(value = "lastnName", required = false) String lastName){
        if(lastName != null)  return idToStudent.values().stream()
                .filter((student) -> student.getLastName().equals(lastName))
                .collect(Collectors.toList());
        else return idToStudent.values();
    }

}
