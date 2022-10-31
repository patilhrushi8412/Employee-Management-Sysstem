package com.te.ems.wrapper;

import com.te.ems.documents.EmployeeExtraDetails;
import com.te.ems.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper {
	
	private Employee employee;
	private EmployeeExtraDetails extraEmployeeDetails;
}
