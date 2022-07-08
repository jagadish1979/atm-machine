package com.zinkworks.atm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zinkworks.atm.domain.LoginDetails;
import com.zinkworks.atm.domain.ValidateAccountRequest;
import com.zinkworks.atm.service.AtmService;

@Controller
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	AtmService atmService;

	@GetMapping(value = "/")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest servletRequest) throws Exception {
		HttpSession session = servletRequest.getSession(true);
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login(LoginDetails login, HttpServletRequest servletRequest) throws Exception {
		try {
			ValidateAccountRequest request = new ValidateAccountRequest();
			request.setAccountNo(login.getAccountNo());
			request.setPin(login.getPinNo());
			atmService.validateAccount(request);
			HttpSession session = servletRequest.getSession(true);
			session.setAttribute("accountNo", login.getAccountNo());
			session.setAttribute("pinNo", login.getPinNo());
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return "redirect:/main";
	}

}
