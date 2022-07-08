package com.zinkworks.atm.domain;

import java.util.Date;

public class TransactionDetails {

	private String transactionId;

	private Date transactionDate;

	private String accountNo;
	
	private int openingBalance;

	private int withdrawal;

	private int deposit;

	private int closingBalance;

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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
	 * @return the withdrawal
	 */
	public int getWithdrawal() {
		return withdrawal;
	}

	/**
	 * @param withdrawal the withdrawal to set
	 */
	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}

	/**
	 * @return the deposit
	 */
	public int getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the closingBalance
	 */
	public int getClosingBalance() {
		return closingBalance;
	}

	/**
	 * @param closingBalance the closingBalance to set
	 */
	public void setClosingBalance(int closingBalance) {
		this.closingBalance = closingBalance;
	}

}
