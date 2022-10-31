package com.te.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ems.entity.Employee;

@Repository
public interface Reposetory extends JpaRepository<Employee, Integer> {

}
