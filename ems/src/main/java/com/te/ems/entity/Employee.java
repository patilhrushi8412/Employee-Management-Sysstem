package com.te.ems.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeid;
	private String firstname;
	private String lastname;
	private String email;
	private long phonenumber;
	private int age;
	private double salary;
	private String experience;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid")
	List<Address> address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid")
	List<Department> department;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid")
	List<BankDetails> bank;
}
