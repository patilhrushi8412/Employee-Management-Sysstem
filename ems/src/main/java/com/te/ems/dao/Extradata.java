package com.te.ems.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.te.ems.documents.EmployeeExtraDetails;

@Repository
public interface Extradata extends MongoRepository<EmployeeExtraDetails, Integer> {

}
