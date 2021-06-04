package com.vfoprojects.produtoapi.services.exceptions;

public class IntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegrityViolationException(String msg) {
		super(msg);
	}

}
