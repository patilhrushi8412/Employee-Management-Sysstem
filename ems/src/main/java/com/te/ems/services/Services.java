package com.te.ems.services;

import java.util.List;

import com.te.ems.entity.Employee;
import com.te.ems.wrapper.Wrapper;

public interface Services {

	Wrapper addemployee(Wrapper employee);

	Wrapper getemployeedetails(int id);

	String deleteemployeedetails(int id);

	Employee updatedetails(Employee employees);

	List<Employee> getalldetails();
}
