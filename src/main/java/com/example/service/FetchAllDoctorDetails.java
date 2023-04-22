package com.example.service;

import com.example.entity.DoctorDetails;
import com.example.repository.DoctorDetailsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchAllDoctorDetails implements DataFetcher<List<DoctorDetails>> {

    @Autowired
    private DoctorDetailsRepository doctorDetailsRepository;

    @Override
    public List<DoctorDetails> get(DataFetchingEnvironment environment) throws Exception {
        return doctorDetailsRepository.findAll();
    }


}
