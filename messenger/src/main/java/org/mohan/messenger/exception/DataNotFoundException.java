package org.mohan.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3895802164522971393L;

	public DataNotFoundException(String message) {
		super(message); // calling parent exception
	}
}
