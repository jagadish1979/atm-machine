package com.zinkworks.atm.domain;

public class DepositRequest {

	private int atmId=1;

	private String accountNo;

	private int denomination_5;

	private int denomination_10;

	private int denomination_20;

	private int denomination_50;

	private int amount;

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
	 * @return the denomination_5
	 */
	public int getDenomination_5() {
		return denomination_5;
	}

	/**
	 * @param denomination_5 the denomination_5 to set
	 */
	public void setDenomination_5(int denomination_5) {
		this.denomination_5 = denomination_5;
	}

	/**
	 * @return the denomination_10
	 */
	public int getDenomination_10() {
		return denomination_10;
	}

	/**
	 * @param denomination_10 the denomination_10 to set
	 */
	public void setDenomination_10(int denomination_10) {
		this.denomination_10 = denomination_10;
	}

	/**
	 * @return the denomination_20
	 */
	public int getDenomination_20() {
		return denomination_20;
	}

	/**
	 * @param denomination_20 the denomination_20 to set
	 */
	public void setDenomination_20(int denomination_20) {
		this.denomination_20 = denomination_20;
	}

	/**
	 * @return the denomination_50
	 */
	public int getDenomination_50() {
		return denomination_50;
	}

	/**
	 * @param denomination_50 the denomination_50 to set
	 */
	public void setDenomination_50(int denomination_50) {
		this.denomination_50 = denomination_50;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
		return "DepositRequest [atmId=" + atmId + ", accountNo=" + accountNo + ", denomination_5=" + denomination_5
				+ ", denomination_10=" + denomination_10 + ", denomination_20=" + denomination_20 + ", denomination_50="
				+ denomination_50 + ", amount=" + amount + ", pin=" + pin + "]";
	}

}
