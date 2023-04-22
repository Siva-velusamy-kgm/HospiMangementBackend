package com.example.repository;

import com.example.entity.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Integer> {

}
