package com.demo.microuser.domain;



public class ApplicationException extends RuntimeException {

	
	private static final long serialVersionUID = 5565760508056698922L;
	
	private WebErrorCode errorCode;

	public ApplicationException(WebErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ApplicationException() {
		super();
	}

	public ApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public ApplicationException(WebErrorCode errorCode,String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.errorCode = errorCode;
	}

	public ApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public ApplicationException(WebErrorCode errorCode,String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errorCode = errorCode;
	}

	public ApplicationException(String arg0) {
		super(arg0);
	}
	
	public ApplicationException(WebErrorCode errorCode,String arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public ApplicationException(Throwable arg0) {
		super(arg0);
	}
	
	public ApplicationException(WebErrorCode errorCode,Throwable arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public WebErrorCode getErrorCode() {
		if(errorCode==null){
			return WebErrorCode.UNKOWN;
		}
		return errorCode;
	}

	public void setErrorCode(WebErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
