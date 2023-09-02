package com.sms.controller;

import com.sms.model.Course;
import com.sms.model.Student;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/admitStudent")
    public ResponseEntity<Student> admitStudent(@RequestBody Student student) {
        Student st=adminService.admitStudent(student);
        return new ResponseEntity<>(st, HttpStatus.CREATED);
    }

    @PostMapping("/admin/uploadCourse")
    public ResponseEntity<Course> uploadCourse(@RequestBody Course course) {
        Course co=adminService.uploadCourse(course);
        return new ResponseEntity<>(co, HttpStatus.CREATED);
    }

    @PostMapping("/admin/assignCourse/{studentId}/{courseId}")
    public ResponseEntity<Student> assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student st=adminService.assignCourse(studentId,courseId);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @GetMapping("/admin/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name) {
        List<Student> studentList=adminService.getStudentByName(name);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/admin/studentsByCourse/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        List<Student> studentList=adminService.getStudentsByCourse(courseId);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
}
