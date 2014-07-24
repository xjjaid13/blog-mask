package com.exception;

public class FunctionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FunctionException(String msg) {
        super(msg);
    }

    public FunctionException(String msg, Throwable t) {
        super(msg, t);
    }

    public FunctionException(Throwable t) {
        super(t);
    }
	
}
