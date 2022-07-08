package com.zinkworks.atm.service;

import com.zinkworks.atm.domain.AccountDetails;
import com.zinkworks.atm.domain.BalanceEnquiryRequest;
import com.zinkworks.atm.domain.BalanceEnquiryResponse;
import com.zinkworks.atm.domain.DepositRequest;
import com.zinkworks.atm.domain.DepositResponse;
import com.zinkworks.atm.domain.MiniStatementRequest;
import com.zinkworks.atm.domain.MiniStatementResponse;
import com.zinkworks.atm.domain.ValidateAccountRequest;
import com.zinkworks.atm.domain.ValidateAccountResponse;
import com.zinkworks.atm.domain.WithdrawalRequest;
import com.zinkworks.atm.domain.WithdrawalResponse;

public interface AtmService {

	ValidateAccountResponse validateAccount(ValidateAccountRequest request) throws Exception;

	DepositResponse deposit(DepositRequest request) throws Exception;

	WithdrawalResponse withdraw(WithdrawalRequest request) throws Exception;

	MiniStatementResponse ministatement(MiniStatementRequest request) throws Exception;

	BalanceEnquiryResponse balanceEnquiry(BalanceEnquiryRequest request) throws Exception;

	AccountDetails fetchAccountDetails(String accountNo) throws Exception;

}
