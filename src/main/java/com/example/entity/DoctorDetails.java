package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "doctor_details")
@Entity
public class DoctorDetails {
    @Id
    private int id;
    private String name;
    private int age;
    private int experience;
}
