package com.abc.exception;

public class DepotIsFullException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DepotIsFullException(String depotName) {
		super(depotName);
	}
}
