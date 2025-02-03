package com.Assignment1_n01635700.services;

import com.Assignment1_n01635700.entities.Students;
import java.util.Map;

public interface StudentsService {
    void addStudent(Students student);
    Map<Integer, Students> getAllStudents();
    Students getStudentById(int id);
    void updateStudent(Students student);
    void deleteStudent(int id);
}