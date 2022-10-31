package com.te.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.ems.controller.EmployeeController;
import com.te.ems.entity.Employee;
import com.te.ems.responc.Responce;
import com.te.ems.services.Services;
import com.te.ems.wrapper.Wrapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

	private MockMvc mockmvc;

	@InjectMocks
	EmployeeController controller;

	@Mock
	Services services;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void TestDeleteemployeeDetails() throws Exception {
		mockmvc.perform(delete("/deletedetailsofemployee/{id}", 4).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
	}

	@Test
	public void testAddedEmployee() throws Exception {
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().firstname("hrushi").build()).build();
		String AsString = objectMapper.writeValueAsString(wrapper);

		when(services.addemployee(Mockito.any())).thenReturn(wrapper);
		String contentAsString = mockmvc
				.perform(post("/addemployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
		Responce response = objectMapper.readValue(contentAsString, Responce.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) response.getData();
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) map.get("employee");
		assertEquals(wrapper.getEmployee().getFirstname(), map1.get("firstname"));
	}

	@Test
	public void testGetEmployee() throws Exception {
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().email("hrushi@gmail.com").build()).build();
		String AsString = objectMapper.writeValueAsString(wrapper);

		when(services.getemployeedetails(4)).thenReturn(wrapper);
		String contentAsString = mockmvc
				.perform(get("/getdetailsofemployee/{id}", 4).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();

		Responce response = objectMapper.readValue(contentAsString, Responce.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) response.getData();
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) map.get("employee");
		assertEquals(wrapper.getEmployee().getEmail(), map1.get("email"));
	}

	@Test
	public void TestGetAllDetails() throws Exception {
		List<Employee> list = Arrays.asList(Employee.builder().firstname("Hrushikesh").lastname("Patil").build(),
				Employee.builder().firstname("Ravi").lastname("Sonavane").build());
		String AsString = objectMapper.writeValueAsString(list);
		when(services.getalldetails()).thenReturn(list);

		String contentAsString = mockmvc
				.perform(get("/allthedetailsofemployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString(); 

		Responce response = objectMapper.readValue(contentAsString, Responce.class);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) response.getData();
		Map<String, Object> map = list1.get(1);
		assertEquals(list.get(1).getFirstname(), map.get("firstname"));
	}
 
	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = Employee.builder().firstname("hrushi").email("rushi@gmail.com").build();
		String AsString = objectMapper.writeValueAsString(employee);

		when(services.updatedetails(Mockito.any())).thenReturn(employee);
		String contentAsString = mockmvc
				.perform(put("/updateemployeedetails").contentType(MediaType.APPLICATION_JSON_VALUE).content(AsString))
				.andExpect(status().isOk()).andReturn().getResponse() // .getContentType();
				.getContentAsString();
		Responce response = objectMapper.readValue(contentAsString, Responce.class);
		@SuppressWarnings("unchecked")
		Map<String, String> map1 = (Map<String, String>) response.getData();
		assertEquals(employee.getFirstname(), map1.get("firstname"));
		assertEquals(employee.getEmail(), map1.get("email"));
	}
}
