package com.zinkworks.atm.domain;

public class ExceptionSchema {

	private String errorCode;
	private String errorDescription;
	private String errorSource;

	/**
	 * 
	 */
	public ExceptionSchema() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorDescription
	 * @param errorSource
	 */
	public ExceptionSchema(String errorCode, String errorDescription, String errorSource) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.errorSource = errorSource;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the errorSource
	 */
	public String getErrorSource() {
		return errorSource;
	}

	/**
	 * @param errorSource the errorSource to set
	 */
	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}

}
