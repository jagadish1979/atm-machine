package com.zinkworks.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.zinkworks.atm.service.AtmService;

@RestController
@RequestMapping("/")
public class AtmRestController {

	@Autowired
	AtmService atmService;

	@PostMapping("validateAccount")
	public ResponseEntity<ValidateAccountResponse> deposit(@RequestBody ValidateAccountRequest request) throws Exception {
		return ResponseEntity.ok(atmService.validateAccount(request));
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequest request) throws Exception {
		return ResponseEntity.ok(atmService.deposit(request));
	}

	@PostMapping("/withdraw")
	public ResponseEntity<WithdrawalResponse> withdraw(@RequestBody WithdrawalRequest request) throws Exception {
		return ResponseEntity.ok(atmService.withdraw(request));
	}

	@PostMapping("miniStatement")
	public ResponseEntity<MiniStatementResponse> miniStatement(@RequestBody MiniStatementRequest request)
			throws Exception {
		return ResponseEntity.ok(atmService.ministatement(request));
	}

	@PostMapping("balanceEnquiry")
	public ResponseEntity<BalanceEnquiryResponse> balanceEnquiry(@RequestBody BalanceEnquiryRequest request)
			throws Exception {
		return ResponseEntity.ok(atmService.balanceEnquiry(request));
	}

}
