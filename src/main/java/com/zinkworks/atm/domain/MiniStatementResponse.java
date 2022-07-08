package com.zinkworks.atm.domain;

import java.util.List;

public class MiniStatementResponse {

	private List<TransactionDetails> transactions;

	/**
	 * @return the transactions
	 */
	public List<TransactionDetails> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<TransactionDetails> transactions) {
		this.transactions = transactions;
	}

}
