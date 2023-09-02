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
public class AdminServiceImpl implements AdminService{

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public Student admitStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Course uploadCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Student assignCourse(Long studentId, Long courseId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            Course course = optionalCourse.get();


            student.getCourses().add(course);
            return studentRepo.save(student);
        } else {
            throw new StudentException("Student or Course not found");
        }
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    @Override
    public List<Student> getStudentsByCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            return  course.getStudents();
        } else {
            throw new CourseException("Course not found");
        }
    }


}
