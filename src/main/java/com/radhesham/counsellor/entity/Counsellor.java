package com.radhesham.counsellor.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "m_counsellor")
public class Counsellor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer counsellorId;

    @Column(name = "username")
    String name;

    @Column(name = "email_id",unique = true)
    String emailId;

    @Column(name="contact")
    String contact;

    @Column(name = "password")
    String password;

    @Column(name = "password_status")
    String passwordStatus;

    @Column(name = "account_status")
    String accountStatus;

    @Column(name = "create_time",updatable = false)
    @CreationTimestamp()
    Date createdTime;

    @Column(name = "updated_time",insertable = false)
    @UpdateTimestamp
    Date updatedTime;
}
