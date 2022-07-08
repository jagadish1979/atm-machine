package com.zinkworks.atm.exception;

/**
 * @author Jagadish Anala
 *
 */

public class AtmException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorDescription;

	private String errorSource = "ATM";

	private String requestUrl;

	private Object requestObject;

	private String method;

	public AtmException() {
		super();
	}

	public AtmException(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Object getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
