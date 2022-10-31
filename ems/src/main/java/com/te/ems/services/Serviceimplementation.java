package com.te.ems.services;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.te.ems.customexception.EmployeeNotFoundException;
import com.te.ems.dao.Extradata;
import com.te.ems.dao.Reposetory;
import com.te.ems.documents.EmployeeExtraDetails;
import com.te.ems.entity.Employee;
import com.te.ems.wrapper.Wrapper;

@Service
public class Serviceimplementation implements Services {

	@Autowired
	private Extradata extradata;

	@Autowired
	private Reposetory reposetory;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Transactional
	public Wrapper addemployee(Wrapper employees) {
		try {
			Employee employee = employees.getEmployee();
			Employee save = reposetory.save(employee);
			employees.getExtraEmployeeDetails().setId(employees.getEmployee().getEmployeeid());
			EmployeeExtraDetails details = employees.getExtraEmployeeDetails();
			EmployeeExtraDetails save3 = extradata.save(details);
			employees.setEmployee(save);
			employees.setExtraEmployeeDetails(save3);
			if (save != null) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("patilhrushikesh525@gmail.com");
				message.setTo(save.getEmail());
				message.setText(save.getFirstname() + " " + save.getLastname() + " Your Registration Done Succesfully");
				message.setSubject("Welcome To Techno Elavate");
				mailSender.send(message);
				System.out.println("Mail Sent Succesfully");
				return employees;
			} else {
				throw new EmployeeNotFoundException("Employee Details Not Inserted");
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public Wrapper getemployeedetails(int id) {

		try {
			Wrapper wrapper = new Wrapper();
			Optional<Employee> findById = reposetory.findById(id);
			if (findById.isEmpty()) {
				throw new EmployeeNotFoundException("Employee Not Found On This ID");
			} else {
				Optional<EmployeeExtraDetails> findById2 = extradata.findById(findById.get().getEmployeeid());
				wrapper.setEmployee(findById.get());
				wrapper.setExtraEmployeeDetails(findById2.get());
				return wrapper;
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public String deleteemployeedetails(int id) {
		try {
			Optional<Employee> findById = reposetory.findById(id);
			if (!findById.isPresent()) {
				throw new EmployeeNotFoundException("Employee Not Present On This ID");
			} else {
				Employee employee = findById.get();
				reposetory.delete(employee);

				Optional<EmployeeExtraDetails> optional = extradata.findById(id);
				if (!optional.isPresent()) {
					throw new EmployeeNotFoundException("Employee Not Present On This ID");
				} else {
					EmployeeExtraDetails employee2 = optional.get();
					extradata.delete(employee2);
					return "Employee Deleted Succesfully";
				}
			}

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public Employee updatedetails(Employee employee) {
		Optional<Employee> findById = reposetory.findById(employee.getEmployeeid());
		try {
			if (!findById.isPresent()) {
				throw new EmployeeNotFoundException("Employee Not Found Select Correct Employee ID");
			} else {
				findById.get().setFirstname(employee.getFirstname());
				findById.get().setLastname(employee.getLastname());
				findById.get().setPhonenumber(employee.getPhonenumber());
				findById.get().setAge(employee.getAge());
				findById.get().setExperience(employee.getExperience());
				findById.get().setSalary(employee.getSalary());
				findById.get().setEmail(employee.getEmail());
				findById.get().setAddress(employee.getAddress());
				findById.get().setBank(employee.getBank());
				findById.get().setDepartment(employee.getDepartment());
				reposetory.save(findById.get());
				return findById.get();
			}
		} catch (RuntimeException e) {
			throw e;
		} 
	}

	@Override
	public List<Employee> getalldetails() {

		try {
			List<Employee> findAll = reposetory.findAll();
			if (findAll.isEmpty()) {
				throw new EmployeeNotFoundException("No Employee Details Present");
			} else {
				return findAll;
			}
		} catch (RuntimeException e) {
			throw e;
		} 
	}
}
