package com.sms.service;

import com.sms.model.Course;
import com.sms.model.Student;

import java.util.List;

public interface AdminService {

    Student admitStudent(Student student);

    Course uploadCourse(Course course);


    Student assignCourse(Long studentId, Long courseId);

    List<Student> getStudentByName(String name);

    List<Student> getStudentsByCourse(Long courseId);
}
