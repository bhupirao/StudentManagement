package com.sms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter

public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String parentsName;
    private String mobileNumber;
    private String dateOfBirth;
    private String gender;
    private String uniqueStudentCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAddress> addresses = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

}
