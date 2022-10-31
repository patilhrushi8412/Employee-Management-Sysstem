package com.te.ems.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.ems.documents.EmployeeExtraDetails;
import com.te.ems.entity.Employee;
import com.te.ems.wrapper.Wrapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PojoTests {
	
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
	}

	private String json="{\"firstname\":\"Hrushi\"}";
	

	@Test
	public void checkPojoWrapper() throws JsonMappingException, JsonProcessingException {
		Employee readValue = mapper.readValue(json, Employee.class);
		Wrapper wrapper = Wrapper.builder().employee(Employee.builder().firstname("Hrushi").lastname("Patil").build()).build();
		assertEquals(readValue.getFirstname(),wrapper.getEmployee().getFirstname() );
	}

}
