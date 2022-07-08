package com.zinkworks.atm.domain;

public class ValidateAccountResponse {

	private String message;

	private boolean isValidAccount = false;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the isValidAccount
	 */
	public boolean isValidAccount() {
		return isValidAccount;
	}

	/**
	 * @param isValidAccount the isValidAccount to set
	 */
	public void setValidAccount(boolean isValidAccount) {
		this.isValidAccount = isValidAccount;
	}

}
