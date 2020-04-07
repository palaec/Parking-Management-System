package com.abc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusNotfoundException.class)
	public ResponseEntity<Object> exception(BusNotfoundException exception) {
		return new ResponseEntity<>("Bus not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DepotNotfoundException.class)
	public ResponseEntity<Object> exception(DepotNotfoundException exception) {
		return new ResponseEntity<>("Depot not found", HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("deprecation")
	@ExceptionHandler(DepotIsFullException.class)
	public ResponseEntity<Object> exception(DepotIsFullException exception) {
		return new ResponseEntity<>(exception.getMessage() + ": Depot is full",
				HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE);
	}

	@ExceptionHandler(BusAlreadyParkedInDepotException.class)
	public ResponseEntity<Object> exception(BusAlreadyParkedInDepotException exception) {
		return new ResponseEntity<>("Bus already parked in Depot", HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(BusNotParkedInAnyDepotException.class)
	public ResponseEntity<Object> exception(BusNotParkedInAnyDepotException exception) {
		return new ResponseEntity<>("Bus Not parked in any Depot", HttpStatus.NOT_FOUND);
	}

}