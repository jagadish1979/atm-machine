package com.zinkworks.atm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkworks.atm.dao.AtmDao;
import com.zinkworks.atm.domain.AccountDetails;
import com.zinkworks.atm.domain.Atm;
import com.zinkworks.atm.domain.BalanceEnquiryRequest;
import com.zinkworks.atm.domain.BalanceEnquiryResponse;
import com.zinkworks.atm.domain.DepositRequest;
import com.zinkworks.atm.domain.DepositResponse;
import com.zinkworks.atm.domain.MiniStatementRequest;
import com.zinkworks.atm.domain.MiniStatementResponse;
import com.zinkworks.atm.domain.TransactionDetails;
import com.zinkworks.atm.domain.ValidateAccountRequest;
import com.zinkworks.atm.domain.ValidateAccountResponse;
import com.zinkworks.atm.domain.WithdrawalRequest;
import com.zinkworks.atm.domain.WithdrawalResponse;
import com.zinkworks.atm.exception.AtmException;
import com.zinkworks.atm.properties.Errors;
import com.zinkworks.atm.service.AtmService;


@Service
public class AtmServiceImpl implements AtmService {

	private static final Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);

	@Autowired
	AtmDao atmDao;

	@Autowired
	Errors errors;
	
	@Override
	public ValidateAccountResponse validateAccount(ValidateAccountRequest request) throws Exception {
		ValidateAccountResponse response = new ValidateAccountResponse();
		try {
			AccountDetails account = atmDao.validateAccountDetails(request.getAccountNo(), request.getPin());
			response.setMessage(errors.getVALIDATED_SUCCESSFULLY());
			response.setValidAccount(true);
		}
		catch(Exception e) {
			throw e;
		}
		return response;
	}
	
	@Override
	public DepositResponse deposit(DepositRequest request) throws Exception {
		DepositResponse response = new DepositResponse();
		int isDeposited = 0;
		try {
			AccountDetails account = atmDao.validateAccountDetails(request.getAccountNo(), request.getPin());

			Map<Integer, Integer> depositDenominations = getDefaultWithdrawalDenominations();

			isDeposited = atmDao.deposit(account, depositDenominations, request.getAtmId(), request.getAmount());

			int closingBalance = request.getAmount();
			if (account != null) {
				closingBalance = account.getOpeningBalance() + request.getAmount();
			}

			if (isDeposited == 1) {
				response.setBalance(closingBalance);
				response.setMessage(errors.getDEPOSITED_SUCCESSFULLY());
			} else {
				response.setBalance(0);
				response.setMessage("Error while performing cash deposit. Please try again after some time !");
			}
		} catch (Exception e) {
			throw e;
		} finally {

		}
		return response;
	}

	@Override
	public WithdrawalResponse withdraw(WithdrawalRequest request) throws Exception {
		WithdrawalResponse response = new WithdrawalResponse();
		int isWithdrawn = 0;
		try {
			Map<Integer, Integer> withdrawalDenominations = getDefaultWithdrawalDenominations();
			List<Integer> availableDenominations = Arrays.asList(50, 20, 10, 5);
			int smallestDenomination = 5;
			AccountDetails account = atmDao.validateAccountDetails(request.getAccountNo(), request.getPin());

			// Insufficient funds scenario
			if (account.getOpeningBalance() + account.getOverDraft() < request.getAmount()) {
				logger.info(errors.getINSUFFICIENT_FUNDS());
				throw new AtmException("400", errors.getINSUFFICIENT_FUNDS());
			}

			Atm atm = atmDao.fetchAvailabletAtmDenominations(request.getAtmId());

			Map<Integer, Integer> atmDenominations = atm.getDenominations();

			logger.error("atmDenominations : " + atmDenominations);

			// when denominations not available scenario
			if (atmDenominations == null) {
				logger.error(errors.getUNABLE_TO_GET_DENOMINATIONS());
				throw new AtmException("400", errors.getUNABLE_TO_DISPENSE_CASH());
			}

			Integer requestedAmount = request.getAmount();

			// invalid amount entered scenario
			if (requestedAmount == null || requestedAmount <= 0) {
				throw new AtmException("400", errors.getVALID_AMOUNT_TO_WITHDRAW());
			}

			// denominations not available for the requested amount scenario
			if (requestedAmount % smallestDenomination != 0) {
				logger.error(errors.getDENOMINATIONS_AVAILABLE(),  availableDenominations.toString());
				throw new AtmException("400",
						"Denominations " + availableDenominations.toString() + " are available for withdrawal !");
			}

			if (atmDenominations != null) {

				Integer atmAmount = atm.getAtmAmount();
				logger.error("atmAmount [ " + atmAmount + " ]");

				// atm does not have the requested amount scenario
				if (atmAmount == null || atmAmount < requestedAmount) {
					logger.error("Requested amount is not available in the ATM ********!");
					throw new AtmException("400", errors.getUNABLE_TO_DISPENSE_CASH());
				}

				int countOfDenomination = 0;
				for (int i = 0; i < availableDenominations.size() && requestedAmount != 0; i++) {
					if (requestedAmount >= availableDenominations.get(i)) {
						countOfDenomination = requestedAmount / availableDenominations.get(i);
						if (countOfDenomination > atmDenominations.get(availableDenominations.get(i))) {
							withdrawalDenominations.put(availableDenominations.get(i),
									atmDenominations.get(availableDenominations.get(i)));
							requestedAmount = requestedAmount - (availableDenominations.get(i)
									* atmDenominations.get(availableDenominations.get(i)));
							logger.info(availableDenominations.get(i) + "'s" + " :"
									+ atmDenominations.get(availableDenominations.get(i)));
						} else {
							withdrawalDenominations.put(availableDenominations.get(i), countOfDenomination);
							logger.info(availableDenominations.get(i) + "'s" + " :" + countOfDenomination);
							requestedAmount = requestedAmount % availableDenominations.get(i);
						}
					}

				}
				logger.error("withdrawalDenominations [ " + withdrawalDenominations + " ]");
			}

			int deductFromBalance = request.getAmount();
			int deductFromOverdraft = 0;

			if (request.getAmount() > account.getOpeningBalance()) {
				deductFromBalance = account.getOpeningBalance();
				deductFromOverdraft = request.getAmount() - deductFromBalance;
			}

			isWithdrawn = atmDao.withdraw(request.getAtmId(), deductFromBalance, deductFromOverdraft,
					request.getAmount(), account, withdrawalDenominations);

			int closingBalance = request.getAmount();
			if (account != null) {
				if (request.getAmount() > account.getOpeningBalance())
					closingBalance = 0;
				else
					closingBalance = account.getOpeningBalance() - request.getAmount();
			}

			if (isWithdrawn == 1) {
				response.setBalance(closingBalance);
				response.setWithdrawalDenominations(withdrawalDenominations);
				response.setMessage(errors.getWITHDRAWAL_SUCCESSFUL());
			} else {
				response.setBalance(account.getOpeningBalance());
				response.setMessage("Error while performing cash withdrawal. Please try again after some time !");
			}
		} catch (Exception e) {
			throw e;
		} finally {

		}
		return response;
	}

	@Override
	public MiniStatementResponse ministatement(MiniStatementRequest request) throws Exception {
		MiniStatementResponse response = new MiniStatementResponse();
		List<TransactionDetails> list = new ArrayList<>();
		try {
			atmDao.validateAccountDetails(request.getAccountNo(), request.getPin());
			list = atmDao.fetchMinistatement(request.getAccountNo());
			response.setTransactions(list);
		} catch (Exception e) {

		} finally {

		}
		return response;
	}

	@Override
	public BalanceEnquiryResponse balanceEnquiry(BalanceEnquiryRequest request) throws Exception {
		BalanceEnquiryResponse response = new BalanceEnquiryResponse();
		try {
			AccountDetails account = atmDao.validateAccountDetails(request.getAccountNo(), request.getPin());
			response.setAccountNo(account.getAccountNo());
			response.setBalance(account.getOpeningBalance());
			response.setOverDraft(account.getOverDraft());
		} catch (Exception e) {
			logger.error("Exception while getting balance of an account : " + e);
			throw e;
		} finally {

		}
		return response;
	}
	
	@Override
	public AccountDetails fetchAccountDetails(String accountNo) throws Exception {
		AccountDetails account = new AccountDetails();
		try {
			 account = atmDao.fetchAccountDetails(accountNo);
		} catch (Exception e) {
			logger.error("Exception while getting account details: " + e);
			throw e;
		} finally {

		}
		return account;
	}

	private Map<Integer, Integer> getDefaultWithdrawalDenominations() {
		Map<Integer, Integer> withdrawalDenominations = new HashMap<>();
		withdrawalDenominations.put(5, 0);
		withdrawalDenominations.put(10, 0);
		withdrawalDenominations.put(20, 0);
		withdrawalDenominations.put(50, 0);
		return withdrawalDenominations;
	}

}
