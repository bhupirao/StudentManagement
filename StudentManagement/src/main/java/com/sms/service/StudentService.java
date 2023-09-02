package com.sms.service;

import com.sms.model.Course;
import com.sms.model.Student;

import java.util.List;

public interface StudentService {

    Student updateProfile(Long studentId, Student updatedStudent);

    List<Course> searchAssignedCourses(Long studentId);

    void leaveCourse(Long studentId, Long courseId);



}
