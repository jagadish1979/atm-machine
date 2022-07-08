package com.zinkworks.atm.domain;

public class WithdrawalRequest {

	private int atmId=1;

	private String accountNo;

	private int pin;
	
	private Integer amount;

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

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WithdrawalRequest [atmId=" + atmId + ", accountNo=" + accountNo + ", pin=***, amount=" + amount
				+ "]";
	}

}
