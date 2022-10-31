package com.te.ems.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "details")
@Data
@Builder
public class EmployeeExtraDetails {

	@Id
	private int id;
	private String empCode;
	private String officialMail;
	private double ctc;
	private String reportingManager;
	private String leave;
}
