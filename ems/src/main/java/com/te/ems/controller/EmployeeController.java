package com.te.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.ems.entity.Employee;
import com.te.ems.responc.Responce;
import com.te.ems.services.Services;
import com.te.ems.wrapper.Wrapper;

@RestController
public class EmployeeController {

	@Autowired
	Services services;

	// Test its Working Or Not
	@GetMapping("/test")
	public String test() {
		return "Working";
	}

	// Adding Details Of Employee
	@PostMapping("/addemployee")
	public ResponseEntity<Responce> addEmployee(@RequestBody Wrapper employee) {
		Wrapper addemployee = services.addemployee(employee);
		return new ResponseEntity<Responce>(new Responce(false, "Employee Added Succesfully", addemployee),
				HttpStatus.OK);
	}

	// Getting details of Employee using Id
	@GetMapping("/getdetailsofemployee/{id}")
	public ResponseEntity<Responce> getEmployeeDetails(@PathVariable int id) {
		Wrapper getemployeedetails = services.getemployeedetails(id);
		return new ResponseEntity<Responce>(new Responce(false, "Employee Details", getemployeedetails), HttpStatus.OK);
	}

	// Delete The Employee Details
	@DeleteMapping("/deletedetailsofemployee/{id}")
	public ResponseEntity<Responce> deleteemployeedetails(@PathVariable int id) {
		String data = services.deleteemployeedetails(id);
		return new ResponseEntity<Responce>(new Responce(false, data, null), HttpStatus.OK);
	}

	// Update Employee Details
	@PutMapping("/updateemployeedetails")
	public ResponseEntity<Responce> updatedetails(@RequestBody Employee employee) {
		Employee updatedetails = services.updatedetails(employee);
		return new ResponseEntity<Responce>(new Responce(false, "Employee Updated Succesfully", updatedetails),
				HttpStatus.OK);
	}

	// Get all the employees details
	@GetMapping("/allthedetailsofemployee")
	public ResponseEntity<Responce> getalldetails() {
		List<Employee> employeelist = services.getalldetails();
		return new ResponseEntity<Responce>(new Responce(false, "All Employee Details", employeelist), HttpStatus.OK);
	}
}
