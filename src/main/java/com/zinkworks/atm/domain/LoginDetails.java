package com.zinkworks.atm.domain;

public class LoginDetails {

	private String accountNo;

	private int pinNo;

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
	 * @return the pinNo
	 */
	public int getPinNo() {
		return pinNo;
	}

	/**
	 * @param pinNo the pingNo to set
	 */
	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}

	@Override
	public String toString() {
		return "LoginDetails [accountNo=" + accountNo + ", pinNo=*** ]";
	}

}
