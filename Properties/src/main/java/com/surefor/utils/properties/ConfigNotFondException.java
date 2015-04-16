package com.surefor.utils.properties;

public class ConfigNotFondException extends Exception {

	public ConfigNotFondException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigNotFondException(String message) {
		super(message);
	}

	public ConfigNotFondException(Throwable cause) {
		super(cause);
	}
}
