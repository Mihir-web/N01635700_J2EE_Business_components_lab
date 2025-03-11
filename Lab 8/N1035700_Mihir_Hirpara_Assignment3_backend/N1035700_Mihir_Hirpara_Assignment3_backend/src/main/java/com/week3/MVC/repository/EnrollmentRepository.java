package com.week3.MVC.repository;

import com.week3.MVC.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Find all enrollments by studentId
    List<Enrollment> findByStudentId(Long studentId);

    // Find enrollment by studentId and programCode (to check if already enrolled)
    Enrollment findByStudentIdAndProgramCode(Long studentId, String programCode);
}
