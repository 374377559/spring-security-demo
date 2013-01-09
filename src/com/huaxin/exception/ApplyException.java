package com.huaxin.exception;

public class ApplyException extends Exception {

	private static final long serialVersionUID = 6596385261286232441L;

	public ApplyException() {
		super();
	}

	public ApplyException(String message) {
		super(message);
	}

	public ApplyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplyException(Throwable cause) {
		super(cause);
	}

}
