package com.zinkworks.atm.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zinkworks.atm.domain.ExceptionSchema;

/**
 * AtmExceptionHandler is a ControllerAdvice which will handle all exceptions
 * related to the APIs
 * 
 * @author Jagadish Anala
 *
 */
@ControllerAdvice
public class AtmExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String BAD_REQUEST = "400";

	private static final String ERROR_SOURCE = "ATM";

	private static final String TECHNICAL_DIFFICULTY = "Technical Difficulty. Please contact support team.";

	@ExceptionHandler(value = AtmException.class)
	public ResponseEntity<Object> exception(AtmException exception) {
		ExceptionSchema ex = new ExceptionSchema(exception.getErrorCode(), exception.getErrorDescription(),
				exception.getErrorSource());
		List<ExceptionSchema> exle = new ArrayList<ExceptionSchema>();
		exle.add(ex);
		return new ResponseEntity<>(exle, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		ExceptionSchema ex = new ExceptionSchema(BAD_REQUEST, TECHNICAL_DIFFICULTY, ERROR_SOURCE);
		return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
	}

}
