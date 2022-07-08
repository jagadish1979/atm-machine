package com.zinkworks.atm.domain;

import java.util.Map;

public class WithdrawalResponse {

	private String message;

	private int balance;

	private Map<Integer, Integer> withdrawalDenominations;
	
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
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the withdrawalDenominations
	 */
	public Map<Integer, Integer> getWithdrawalDenominations() {
		return withdrawalDenominations;
	}

	/**
	 * @param withdrawalDenominations the withdrawalDenominations to set
	 */
	public void setWithdrawalDenominations(Map<Integer, Integer> withdrawalDenominations) {
		this.withdrawalDenominations = withdrawalDenominations;
	}

	@Override
	public String toString() {
		return "WithdrawalResponse [message=" + message + ", balance=" + balance + ", withdrawalDenominations="
				+ withdrawalDenominations + "]";
	}

}
