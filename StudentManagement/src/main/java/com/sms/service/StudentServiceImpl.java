package com.sms.service;

import com.sms.exception.CourseException;
import com.sms.exception.StudentException;
import com.sms.model.Course;
import com.sms.model.Student;
import com.sms.repo.CourseRepo;
import com.sms.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;


    @Override
    public Student updateProfile(Long studentId, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();


            student.setEmail(updatedStudent.getEmail());
            student.setMobileNumber(updatedStudent.getMobileNumber());
            student.setParentsName(updatedStudent.getParentsName());
            student.setAddresses(updatedStudent.getAddresses());

            return studentRepo.save(student);
        } else {
            throw new StudentException("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public List<Course> searchAssignedCourses(Long studentId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return  student.getCourses();
        } else {
            throw new StudentException("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public void leaveCourse(Long studentId, Long courseId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            Course course = optionalCourse.get();

            if (student.getCourses().contains(course)) {
                student.getCourses().remove(course);
                studentRepo.save(student);
            } else {
                throw new CourseException("Student with ID " + studentId +
                        " is not assigned to Course with ID " + courseId);
            }
        } else {
            throw new StudentException("Student with ID " + studentId + " not found");
        }
    }
}
