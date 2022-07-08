package com.zinkworks.atm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zinkworks.atm.domain.AccountDetails;
import com.zinkworks.atm.domain.DepositRequest;
import com.zinkworks.atm.domain.MiniStatementRequest;
import com.zinkworks.atm.domain.MiniStatementResponse;
import com.zinkworks.atm.domain.TransactionDetails;
import com.zinkworks.atm.domain.WithdrawalRequest;
import com.zinkworks.atm.service.AtmService;

@Controller
@RequestMapping("/")
public class AtmWebController {

	@Autowired
	AtmService atmService;

	@GetMapping(value = "/main")
	public String main(Model model, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(true);
		String accountNo = (String) session.getAttribute("accountNo");
		if (accountNo != null) {
			int pinNo = (int) session.getAttribute("pinNo");
			List<TransactionDetails> transactionsList = new ArrayList<>();
			MiniStatementRequest request = new MiniStatementRequest();
			request.setAccountNo(accountNo);
			request.setPin(pinNo);
			MiniStatementResponse miniStatementResponse = new MiniStatementResponse();
			try {
				miniStatementResponse = atmService.ministatement(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			transactionsList = miniStatementResponse.getTransactions();
			model.addAttribute("transactionsList", transactionsList);
			model.addAttribute("currentDate", new SimpleDateFormat("dd MMM yyyy").format(new Date()));

			try {
				AccountDetails account = atmService.fetchAccountDetails(accountNo);
				model.addAttribute("account", account);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "main";
		}
		return "login";
	}

	@GetMapping(value = "/cd")
	public String cd(Model model, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(true);
		String accountNo = (String) session.getAttribute("accountNo");
		if (accountNo != null) {
			return "cashDeposit";
		}
		return "login";
	}

	@GetMapping(value = "/cw")
	public String cw(Model model, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(true);
		String accountNo = (String) session.getAttribute("accountNo");
		if (accountNo != null) {
			return "cashWithdrawal";
		}
		return "login";
	}

	@GetMapping(value = "/be")
	public String be(Model model, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(true);
		String accountNo = (String) session.getAttribute("accountNo");
		if (accountNo != null) {
			try {
				AccountDetails account = atmService.fetchAccountDetails(accountNo);
				model.addAttribute("account", account);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "balanceEnquiry";
		}
		return "login";

	}

	@GetMapping(value = "/ms")
	public String ms(Model model, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(true);
		String accountNo = (String) session.getAttribute("accountNo");
		if (accountNo != null) {
			int pinNo = (int) session.getAttribute("pinNo");
			List<TransactionDetails> transactionsList = new ArrayList<>();
			MiniStatementRequest request = new MiniStatementRequest();
			request.setAccountNo(accountNo);
			request.setPin(pinNo);
			MiniStatementResponse miniStatementResponse = new MiniStatementResponse();
			try {
				miniStatementResponse = atmService.ministatement(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			transactionsList = miniStatementResponse.getTransactions();
			model.addAttribute("transactionsList", transactionsList);
			return "miniStatement";
		}
		return "login";
	}

	@PostMapping("/cashDeposit")
	public String cashDeposit(DepositRequest request, HttpServletRequest servletRequest) throws Exception {
		try {
			HttpSession session = servletRequest.getSession(true);
			String accountNo = (String) session.getAttribute("accountNo");
			int pinNo = (int) session.getAttribute("pinNo");

			request.setAccountNo(accountNo);
			request.setPin(pinNo);
			atmService.deposit(request);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/cashDeposit";
		}
		return "redirect:/main";
	}

	@PostMapping("/cashWithdrawal")
	public String cashWithdrawal(WithdrawalRequest request, HttpServletRequest servletRequest) throws Exception {
		try {
			HttpSession session = servletRequest.getSession(true);
			String accountNo = (String) session.getAttribute("accountNo");
			int pinNo = (int) session.getAttribute("pinNo");

			request.setAccountNo(accountNo);
			request.setPin(pinNo);
			atmService.withdraw(request);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/cashWithdrawal";
		}
		return "redirect:/main";
	}

}
