package com.radhesham.counsellor.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "m_enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer enquiryId;

    @Column(name="name")
    private String name;

    @Column(name="contact")
    private String contact;

    @Column(name = "course")
    private String course;

    @Column(name="class-mode")
    private String classMode;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name="insert_time" ,updatable = false)
    private LocalDate insertTime;

    @UpdateTimestamp
    @Column(name="update_time" ,insertable = false)
    private LocalDate updateTime;

    @ManyToOne
    @JoinColumn(name="counsellor_id")
    private Counsellor counsellor;
}
