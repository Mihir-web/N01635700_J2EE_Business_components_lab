package com.Assignment1_n01635700.services;

import com.Assignment1_n01635700.entities.Students;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentsServiceImpl implements StudentsService {
    private final Map<Integer, Students> studentMap = new HashMap<>();

    @PostConstruct
    public void initializeData() {
        studentMap.put(1, new Students(1, "John Doe", 25, "Male", "john.doe@example.com", "New York", LocalDate.of(1999, 5, 15)));
        studentMap.put(2, new Students(2, "Jane Smith", 28, "Female", "jane.smith@example.com", "Los Angeles", LocalDate.of(1996, 8, 20)));
        studentMap.put(3, new Students(3, "Alice Brown", 22, "Female", "alice.brown@example.com", "Chicago", LocalDate.of(2002, 12, 5)));
    }

    @Override
    public void addStudent(Students student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public Map<Integer, Students> getAllStudents() {
        return studentMap;
    }

    @Override
    public Students getStudentById(int id) {
        return studentMap.get(id);
    }

    @Override
    public void updateStudent(Students student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
        }
    }

    @Override
    public void deleteStudent(int id) {
        studentMap.remove(id);
    }
}