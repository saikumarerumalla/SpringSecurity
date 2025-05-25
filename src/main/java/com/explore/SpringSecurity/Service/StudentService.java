package com.explore.SpringSecurity.Service;


import com.explore.SpringSecurity.Entity.Student;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class StudentService {

    private List<Student>  students = new ArrayList<>(List.of(
           new  Student(1, "Sai", 200),
            new  Student(2, "Naveen", 170)
    ));

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }
}
