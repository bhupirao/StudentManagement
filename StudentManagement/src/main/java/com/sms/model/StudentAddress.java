package com.sms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String area;
    private String state;
    private String district;
    private String pincode;
    private String addressType;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;
}
