package com.sms.controller;

import com.sms.model.Course;
import com.sms.model.Student;
import com.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateProfile(@PathVariable("studentId") Long studentId, @RequestBody Student updatedStudent) {
        Student st=studentService.updateProfile(studentId, updatedStudent);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @GetMapping("/students/courses/{studentId}")
    public ResponseEntity<List<Course>> getAssignedCourses(@PathVariable("studentId") Long studentId) {
        List<Course> courseList=studentService.searchAssignedCourses(studentId);
        return  new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @DeleteMapping("/students/courses/{studentId}/{courseId}")
    public void leaveCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        studentService.leaveCourse(studentId, courseId);
    }
}
