package com.te.ems.customexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.ems.customexception.EmployeeNotFoundException;
import com.te.ems.responc.Responce;

@RestControllerAdvice
public class Exceptionhandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Responce> exceptionhandler(EmployeeNotFoundException exception) {
		return new ResponseEntity<Responce>(new Responce(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
