package com.zinkworks.atm.domain;

public class DepositResponse {

	private String message;

	private int balance;

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

	@Override
	public String toString() {
		return "DepositResponse [message=" + message + ", balance=" + balance + "]";
	}

}
