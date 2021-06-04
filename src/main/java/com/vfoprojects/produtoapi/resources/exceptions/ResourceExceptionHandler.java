package com.vfoprojects.produtoapi.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vfoprojects.produtoapi.services.exceptions.EntityNotFoundException;
import com.vfoprojects.produtoapi.services.exceptions.IntegrityViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(IntegrityViolationException.class)
	public ResponseEntity<StandardError> integrityViolation(IntegrityViolationException e, HttpServletRequest request) {

		StandardError err = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Produto não cadastrado",
				e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Id inexistente",
				e.getMessage(), request.getRequestURI());
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	

}
