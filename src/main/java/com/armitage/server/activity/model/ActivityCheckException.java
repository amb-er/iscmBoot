package com.armitage.server.activity.model;

public class ActivityCheckException extends Exception{

	public ActivityCheckException() {
		super();
	}

	public ActivityCheckException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ActivityCheckException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ActivityCheckException(String arg0) {
		super(arg0);
	}

	public ActivityCheckException(Throwable arg0) {
		super(arg0);
	}

}
