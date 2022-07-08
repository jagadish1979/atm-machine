package com.zinkworks.atm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:errors.properties")
@ConfigurationProperties
public class Errors {

	private String VALIDATED_SUCCESSFULLY;

	private String DEPOSITED_SUCCESSFULLY;

	private String WITHDRAWAL_SUCCESSFUL;

	private String INSUFFICIENT_FUNDS;

	private String UNABLE_TO_GET_DENOMINATIONS;

	private String VALID_AMOUNT_TO_WITHDRAW;

	private String UNABLE_TO_DISPENSE_CASH;

	private String INVALID_ACCOUNT;

	private String INVALID_PIN;

	private String DENOMINATIONS_AVAILABLE;

	public String getVALIDATED_SUCCESSFULLY() {
		return VALIDATED_SUCCESSFULLY;
	}

	public void setVALIDATED_SUCCESSFULLY(String vALIDATED_SUCCESSFULLY) {
		VALIDATED_SUCCESSFULLY = vALIDATED_SUCCESSFULLY;
	}

	public String getDEPOSITED_SUCCESSFULLY() {
		return DEPOSITED_SUCCESSFULLY;
	}

	public void setDEPOSITED_SUCCESSFULLY(String dEPOSITED_SUCCESSFULLY) {
		DEPOSITED_SUCCESSFULLY = dEPOSITED_SUCCESSFULLY;
	}

	public String getWITHDRAWAL_SUCCESSFUL() {
		return WITHDRAWAL_SUCCESSFUL;
	}

	public void setWITHDRAWAL_SUCCESSFUL(String wITHDRAWAL_SUCCESSFUL) {
		WITHDRAWAL_SUCCESSFUL = wITHDRAWAL_SUCCESSFUL;
	}

	public String getINSUFFICIENT_FUNDS() {
		return INSUFFICIENT_FUNDS;
	}

	public void setINSUFFICIENT_FUNDS(String iNSUFFICIENT_FUNDS) {
		INSUFFICIENT_FUNDS = iNSUFFICIENT_FUNDS;
	}

	public String getUNABLE_TO_GET_DENOMINATIONS() {
		return UNABLE_TO_GET_DENOMINATIONS;
	}

	public void setUNABLE_TO_GET_DENOMINATIONS(String uNABLE_TO_GET_DENOMINATIONS) {
		UNABLE_TO_GET_DENOMINATIONS = uNABLE_TO_GET_DENOMINATIONS;
	}

	public String getVALID_AMOUNT_TO_WITHDRAW() {
		return VALID_AMOUNT_TO_WITHDRAW;
	}

	public void setVALID_AMOUNT_TO_WITHDRAW(String vALID_AMOUNT_TO_WITHDRAW) {
		VALID_AMOUNT_TO_WITHDRAW = vALID_AMOUNT_TO_WITHDRAW;
	}

	public String getUNABLE_TO_DISPENSE_CASH() {
		return UNABLE_TO_DISPENSE_CASH;
	}

	public void setUNABLE_TO_DISPENSE_CASH(String uNABLE_TO_DISPENSE_CASH) {
		UNABLE_TO_DISPENSE_CASH = uNABLE_TO_DISPENSE_CASH;
	}

	public String getINVALID_ACCOUNT() {
		return INVALID_ACCOUNT;
	}

	public void setINVALID_ACCOUNT(String iNVALID_ACCOUNT) {
		INVALID_ACCOUNT = iNVALID_ACCOUNT;
	}

	public String getINVALID_PIN() {
		return INVALID_PIN;
	}

	public void setINVALID_PIN(String iNVALID_PIN) {
		INVALID_PIN = iNVALID_PIN;
	}

	public String getDENOMINATIONS_AVAILABLE() {
		return DENOMINATIONS_AVAILABLE;
	}

	public void setDENOMINATIONS_AVAILABLE(String dENOMINATIONS_AVAILABLE) {
		DENOMINATIONS_AVAILABLE = dENOMINATIONS_AVAILABLE;
	}

}
