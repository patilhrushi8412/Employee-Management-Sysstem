package com.te.ems.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.te.ems.entity.Address;
import com.te.ems.entity.BankDetails;
import com.te.ems.entity.Department;

import lombok.Data;

@Data
public class Employeedto {

	private String firstname;
	private String lastname;
	private String email;
	private long phonenumber;
	private int age;
	private double salary;
	private String experience;

	@OneToMany(cascade = CascadeType.ALL)
	List<Address> address;

	@OneToMany(cascade = CascadeType.ALL)
	List<Department> department;

	@OneToMany(cascade = CascadeType.ALL)
	List<BankDetails> bank;
}
