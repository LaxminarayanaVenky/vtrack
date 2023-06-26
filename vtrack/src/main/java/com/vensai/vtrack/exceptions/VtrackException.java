package com.vensai.vtrack.exceptions;

public class VtrackException  extends Exception{
	
	private String message;
	public VtrackException(String message) {
		super(message);
	}

}
