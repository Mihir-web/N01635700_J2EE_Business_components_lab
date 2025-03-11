package com.week3.MVC.service;

import com.week3.MVC.entity.Enrollment;
import com.week3.MVC.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // Get all programs a student is enrolled in
    public List<Enrollment> getEnrolledPrograms(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    // Enroll a student in a program
    public String enrollInProgram(Long studentId, String programCode, Double amountPaid) {
        // Check if the student is already enrolled in the program
        Enrollment existingEnrollment = enrollmentRepository.findByStudentIdAndProgramCode(studentId, programCode);
        if (existingEnrollment != null) {
            return "Already enrolled in this program.";
        }

        // Create a new enrollment
        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setStudentId(studentId);
        newEnrollment.setProgramCode(programCode);
        newEnrollment.setStartDate(new java.util.Date());
        newEnrollment.setAmountPaid(amountPaid);
        newEnrollment.setStatus("enrolled");

        enrollmentRepository.save(newEnrollment);
        return "Successfully enrolled in the program.";
    }

    // Drop a program
    public String dropProgram(Long studentId, long id) {
        // Find the enrollment record by its id
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);  // Find by enrollment ID
        if (enrollment == null) {
            return "Not enrolled in this program.";  // If no enrollment found
        }

        // Check if the studentId matches the enrollment
        if (!enrollment.getStudentId().equals(studentId)) {
            return "This is not your enrollment record.";
        }

        // Mark the program as dropped
        enrollment.setStatus("dropped");
        enrollmentRepository.save(enrollment);
        return "Successfully dropped the program.";
    }

}
