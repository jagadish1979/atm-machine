package com.zinkworks.atm.dao;

import java.util.List;
import java.util.Map;

import com.zinkworks.atm.domain.AccountDetails;
import com.zinkworks.atm.domain.Atm;
import com.zinkworks.atm.domain.TransactionDetails;

public interface AtmDao {

	List<TransactionDetails> fetchMinistatement(String accountNo) throws Exception;

	Atm fetchAvailabletAtmDenominations(int atmId) throws Exception;

	AccountDetails fetchAccountDetails(String accountNo) throws Exception;

	AccountDetails validateAccountDetails(String accountNo, int pin) throws Exception;

	int withdraw(int atmId, int balance, int overdraft, int requestedAmount, AccountDetails account,
			Map<Integer, Integer> withdrawalDenominations) throws Exception;

	int deposit(AccountDetails account, Map<Integer, Integer> depositlDenominations, int atmId, int amount)
			throws Exception;

	
}
