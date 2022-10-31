package com.te.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.ems.dao.Extradata;
import com.te.ems.dao.Reposetory;
import com.te.ems.documents.EmployeeExtraDetails;
import com.te.ems.entity.Employee;
import com.te.ems.services.Serviceimplementation;
import com.te.ems.wrapper.Wrapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTests {

	private MockMvc mockmvc;

	@InjectMocks
	Serviceimplementation services;

	@Mock
	Reposetory repo;

	@Mock
	JavaMailSender mailSender;

	@Mock
	Extradata data;

	ObjectMapper objectMapper = new ObjectMapper();

	// Delete Employee Done
	@Test
	void testDeleteEmployeeDetailsSuccess() throws Exception {
		Employee employee = Employee.builder().firstname("hrushi").email("hruship@gmail.com").lastname("patil").build();
		EmployeeExtraDetails extraDetails = EmployeeExtraDetails.builder().officialMail("rushi@gmail.com").build();
		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		when(data.findById(Mockito.anyInt())).thenReturn(Optional.of(extraDetails));
		String string = services.deleteemployeedetails(Mockito.anyInt());
		assertEquals("Employee Deleted Succesfully", string);
	}

	@Test
	void testDeleteEmployeeDetails_failure() throws Exception {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> services.deleteemployeedetails(145));
		assertEquals("Employee Not Present On This ID", exception.getMessage());
	}

	// getAllDetails Done
	@Test
	void testGetAllemployeeDetails_success() throws Exception {
		List<Employee> list = Arrays.asList(Employee.builder().firstname("Hrushi").build(),
				Employee.builder().firstname("Amar").build());
		when(repo.findAll()).thenReturn(list.stream().toList());
		List<Employee> list1 = services.getalldetails();
		String firstname = list1.get(1).getFirstname();
		assertEquals(firstname, list.get(1).getFirstname());
		assertEquals(2, list1.size());
	}

	@Test
	void testGetAllemployeeDetails_failure() throws Exception {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> services.getalldetails());
		assertEquals("No Employee Details Present", exception.getMessage());
	}

	// Get Employee Done
	@Test
	void testGetemployeeDetailsSucess() throws Exception {
		Employee employee = Employee.builder().firstname("hrushi").email("hruship@gmail.com").lastname("patil").build();
		EmployeeExtraDetails extraDetails = EmployeeExtraDetails.builder().officialMail("rushi@gmail.com").build();
		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		when(data.findById(Mockito.anyInt())).thenReturn(Optional.of(extraDetails));
		Wrapper wrapper = Wrapper.builder().employee(employee).extraEmployeeDetails(extraDetails).build();
		Wrapper newWrap = services.getemployeedetails(Mockito.anyInt());
		assertEquals(employee.getFirstname(), newWrap.getEmployee().getFirstname());
	}

	@Test
	void testGetEmployeeDetailsFailure() throws Exception {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> services.getemployeedetails(855));
		assertEquals("Employee Not Found On This ID", exception.getMessage());
	}

	// Update Done
	@Test
	void testUpdateEmployeeDetails_success() throws Exception {
		Employee employeeRequest = Employee.builder().firstname("Hrushi").email("hrushipatil@gmail.com")
				.lastname("Patil").employeeid(1245).build();
		Employee employee = Employee.builder().firstname(employeeRequest.getFirstname())
				.email(employeeRequest.getEmail()).lastname(employeeRequest.getLastname()).build();
		when(repo.findById(employeeRequest.getEmployeeid())).thenReturn(Optional.of(employee));
		when(repo.save(employee)).thenReturn(employee);
		Employee employee2 = services.updatedetails(employeeRequest);
		assertEquals(employee.getFirstname(), employee2.getFirstname());
	}

	@Test
	void testUpdateEmployeeDetailsfailure() throws Exception {
		Employee employeeRequest = Employee.builder().firstname("Hrushi").email("hrushi@gmail.com").lastname("Patil")
				.employeeid(569).build();
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> services.updatedetails(employeeRequest));
		assertEquals("Employee Not Found Select Correct Employee ID", exception.getMessage());
	}

	// Add Employee Test not Done
	@Test
	void testAddEmployeeSuccess() throws Exception {
		Wrapper wrapper = Wrapper.builder()
				.employee(Employee.builder().firstname("Hrushi").lastname("Patil")
						.email("patilhrushikesh8412@gmail.com").build())
				.extraEmployeeDetails(
						EmployeeExtraDetails.builder().id(45).ctc(47000).reportingManager("Rakesh").build())
				.build();

		when(repo.save(wrapper.getEmployee())).thenReturn(wrapper.getEmployee());
		when(data.save(wrapper.getExtraEmployeeDetails())).thenReturn(wrapper.getExtraEmployeeDetails());
		Wrapper wrap = services.addemployee(wrapper);
		assertEquals(wrapper.getEmployee().getFirstname(), wrap.getEmployee().getFirstname());
	}

	@Test
	void testAddEmployeeDetailsfailure() throws Exception {
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().firstname("Hrushi").lastname("Patil").build())
				.extraEmployeeDetails(
						EmployeeExtraDetails.builder().id(45).ctc(47000).reportingManager("Rakesh").build())
				.build();
		RuntimeException exception = assertThrows(RuntimeException.class, () -> services.addemployee(wrapper));
		assertEquals("Employee Details Not Inserted", exception.getMessage());

	}
}
