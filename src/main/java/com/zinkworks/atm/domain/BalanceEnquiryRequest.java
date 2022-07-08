package com.zinkworks.atm.domain;

public class BalanceEnquiryRequest {

	private int atmId;

	private String accountNo;

	private int pin;

	/**
	 * @return the atmId
	 */
	public int getAtmId() {
		return atmId;
	}

	/**
	 * @param atmId the atmId to set
	 */
	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "BalanceEnquiryRequest [atmId=" + atmId + ", accountNo=" + accountNo + ", pin=" + pin + "]";
	}

}
