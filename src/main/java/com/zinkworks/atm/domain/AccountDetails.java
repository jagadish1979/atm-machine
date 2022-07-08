package com.zinkworks.atm.domain;

public class AccountDetails {

	private String accountNo;

	private int pin;

	private int openingBalance;

	private int overDraft;

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
	 * @param pinNo the pinNo to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	/**
	 * @return the openingBalance
	 */
	public int getOpeningBalance() {
		return openingBalance;
	}

	/**
	 * @param openingBalance the openingBalance to set
	 */
	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}

	/**
	 * @return the overDraft
	 */
	public int getOverDraft() {
		return overDraft;
	}

	/**
	 * @param overDraft the overDraft to set
	 */
	public void setOverDraft(int overDraft) {
		this.overDraft = overDraft;
	}

	@Override
	public String toString() {
		return "AccountDetails [accountNo=" + accountNo + ", pin=***, openingBalance=" + openingBalance
				+ ", overDraft=" + overDraft + "]";
	}

}
