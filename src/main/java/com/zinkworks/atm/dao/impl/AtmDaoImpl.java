package com.zinkworks.atm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zinkworks.atm.dao.AtmDao;
import com.zinkworks.atm.domain.AccountDetails;
import com.zinkworks.atm.domain.Atm;
import com.zinkworks.atm.domain.AtmDetails;
import com.zinkworks.atm.domain.TransactionDetails;
import com.zinkworks.atm.ennum.Denomination;
import com.zinkworks.atm.ennum.TransactionType;
import com.zinkworks.atm.exception.AtmException;
import com.zinkworks.atm.properties.Errors;

@Repository
public class AtmDaoImpl implements AtmDao {

	private static final Logger logger = LoggerFactory.getLogger(AtmDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	Errors errors;
	
	@Transactional
	@Override
	public int deposit(AccountDetails account, Map<Integer, Integer> depositDenominations, int atmId, int amount)
			throws Exception {
		logger.info("Inside deposit method DAO **********!");
		int isDeposited = 0;
		try {
			logger.info("Before Calling depoist query ************!");
			String depositQuery = "update account_details set balance = balance + ? where account_number = ?";
			isDeposited = jdbcTemplate.update(depositQuery, new Object[] { amount, account.getAccountNo() });
			logger.info("After Calling depoist query ************!");
			logger.info("isDeposited {} ", isDeposited);

			int closingBalance = amount;
			if (account != null) {
				closingBalance = account.getOpeningBalance() + amount;
			}

			TransactionDetails tr = new TransactionDetails();
			tr.setAccountNo(account.getAccountNo());
			tr.setDeposit(amount);
			tr.setWithdrawal(0);
			tr.setOpeningBalance(account.getOpeningBalance());

			Date currentDate = new Date();
			tr.setTransactionDate(currentDate);
			tr.setTransactionId(generateUUID());
			tr.setClosingBalance(closingBalance);

			// inserting into transaction
			isDeposited = insertTransaction(tr);

			// updating atm details
			AtmDetails atm = new AtmDetails();
			atm.setId(atmId);
			atm.setAtmAmount(amount);
			atm.setDenomination_5(depositDenominations.get(50));
			atm.setDenomination_10(depositDenominations.get(10));
			atm.setDenomination_20(depositDenominations.get(20));
			atm.setDenomination_50(depositDenominations.get(50));

			isDeposited = updateAtmDetails(TransactionType.DEPOSIT, atm);

		} catch (Exception e) {
			logger.error("Exception while depositing money : " + e);
			throw e;
		} finally {

		}
		return isDeposited;
	}

	@Transactional
	@Override
	public int withdraw(int atmId, int balance, int overdraft, int requestedAmount, AccountDetails account,
			Map<Integer, Integer> withdrawalDenominations) throws Exception {
		logger.info("Inside withdraw method DAO **********!");
		int isUpdated = 0;

		try {
			logger.info("Before Calling withdrawal query ************!");
			String withDrawalQuery = "update account_details set balance = balance - ? , overdraft = overdraft - ? where account_number = ?";
			isUpdated = jdbcTemplate.update(withDrawalQuery,
					new Object[] { balance, overdraft, account.getAccountNo() });
			logger.info("After Calling withdrawal query ************!");
			logger.info("isUpdated {} ", isUpdated);

			int closingBalance = requestedAmount;
			if (account != null) {
				if (requestedAmount > account.getOpeningBalance())
					closingBalance = 0;
				else
					closingBalance = account.getOpeningBalance() - requestedAmount;
			}

			
			TransactionDetails tr = new TransactionDetails();
			tr.setAccountNo(account.getAccountNo());
			tr.setDeposit(0);
			tr.setWithdrawal(requestedAmount);
			tr.setOpeningBalance(account.getOpeningBalance());
			
			Date currentDate = new Date();
			
			tr.setTransactionDate(currentDate);
			tr.setTransactionId(generateUUID());
			tr.setClosingBalance(closingBalance);

			// inserting into transaction
			isUpdated = insertTransaction(tr);

			// updating atm details
			AtmDetails atmDetails = new AtmDetails();
			atmDetails.setId(atmId);
			atmDetails.setAtmAmount(requestedAmount);
			atmDetails.setDenomination_10(withdrawalDenominations.get(10));
			atmDetails.setDenomination_20(withdrawalDenominations.get(20));
			atmDetails.setDenomination_5(withdrawalDenominations.get(5));
			atmDetails.setDenomination_50(withdrawalDenominations.get(50));

			isUpdated = updateAtmDetails(TransactionType.WITHDRAW, atmDetails);

		} catch (Exception e) {
			logger.error("Exception while withdrawing the money : " + e);
			throw e;
		} finally {

		}
		return isUpdated;
	}

	@Override
	public List<TransactionDetails> fetchMinistatement(String accountNo) throws Exception {
		List<TransactionDetails> list = new ArrayList<>();
		String query = "select top 10 transaction_id, transaction_date, account_number, opening_balance, withdrawal, deposit, closing_balance from transaction_details where account_number = ? order by transaction_date desc";
		try {
			RowMapper<TransactionDetails> rowMapper = new RowMapper<TransactionDetails>() {
				@Override
				public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					TransactionDetails tr = new TransactionDetails();
					tr.setTransactionId(rs.getString(1));
					tr.setTransactionDate(rs.getTimestamp(2));
					tr.setAccountNo(rs.getString(3));
					tr.setOpeningBalance(rs.getInt(4));
					tr.setWithdrawal(rs.getInt(5));
					tr.setDeposit(rs.getInt(6));
					tr.setClosingBalance(rs.getInt(7));
					return tr;
				}
			};

			list = jdbcTemplate.query(query, new Object[] { accountNo }, rowMapper);
		} catch (Exception e) {
			logger.error("Exception while fetching ministatement : " + e);
			throw e;
		} finally {

		}
		return list;
	}

	@Override
	public Atm fetchAvailabletAtmDenominations(int atmId) throws Exception {
		Atm atm = new Atm();
		Map<Integer, Integer> denominations = new HashMap<>();
		AtmDetails atmDetails = new AtmDetails();
		String query = "select denomination_5, denomination_10, denomination_20, denomination_50, total_amount from atm_details where atm_id = ?";
		RowMapper<AtmDetails> rowMapper = new RowMapper<AtmDetails>() {

			@Override
			public AtmDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				AtmDetails atm = new AtmDetails();
				atm.setDenomination_5(rs.getInt(1));
				atm.setDenomination_10(rs.getInt(2));
				atm.setDenomination_20(rs.getInt(3));
				atm.setDenomination_50(rs.getInt(4));
				atm.setAtmAmount(rs.getInt(5));
				return atm;
			}
		};

		try {
			logger.info("atmId in fetchAvailableAtmDenominations ====>"+atmId);
			atmDetails = jdbcTemplate.queryForObject(query, new Object[] { atmId }, rowMapper);
			if (atmDetails != null) {
				denominations.put(Denomination.DENOMINATION_5.value(), atmDetails.getDenomination_5());
				denominations.put(Denomination.DENOMINATION_10.value(), atmDetails.getDenomination_10());
				denominations.put(Denomination.DENOMINATION_20.value(), atmDetails.getDenomination_20());
				denominations.put(Denomination.DENOMINATION_50.value(), atmDetails.getDenomination_50());
				atm.setAtmAmount(atmDetails.getAtmAmount()); // available amount in the atm
				atm.setDenominations(denominations);
			}
		} catch (Exception e) {
			logger.error("Exception while fetching denominations available in the atm : " + e);
			throw e;
		} finally {

		}
		return atm;
	}

	@Override
	public AccountDetails fetchAccountDetails(String accountNo) throws Exception {
		AccountDetails account = null;
		String query = "select account_number, balance, overdraft, pin from account_details where account_number = ?";
		RowMapper<AccountDetails> rowMapper = new RowMapper<AccountDetails>() {

			@Override
			public AccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountDetails ac = new AccountDetails();
				ac.setAccountNo(rs.getString(1));
				ac.setOpeningBalance(rs.getInt(2));
				ac.setOverDraft(rs.getInt(3));
				ac.setPin(rs.getInt(4));
				return ac;
			}
		};

		try {
			account = jdbcTemplate.queryForObject(query, new Object[] { accountNo }, rowMapper);
		} catch (Exception e) {

		} finally {

		}
		return account;
	}

	@Transactional
	public int updateAtmDetails(TransactionType transactionType, AtmDetails atm) throws Exception {
		String query = "";
		int updated = 0;
		try {
			switch (transactionType) {
			case DEPOSIT:
				query = "update atm_details set denomination_5 = denomination_5 + ? "
						+ ", denomination_10 = denomination_10 + ? , denomination_20 =  denomination_20 + ? "
						+ ", denomination_50 =  denomination_50 + ? , total_amount = total_amount + ? where atm_id = ? ";
				break;

			case WITHDRAW:
				query = "update atm_details set denomination_5 = denomination_5 - ? "
						+ ", denomination_10 = denomination_10 - ? , denomination_20 =  denomination_20 - ? "
						+ ", denomination_50 =  denomination_50 - ? , total_amount = total_amount - ? where atm_id = ? ";
				break;
			}

			logger.info("updateAtmDetails query ===>" + query);

			updated = jdbcTemplate.update(query, new Object[] { atm.getDenomination_5(), atm.getDenomination_10(),
					atm.getDenomination_20(), atm.getDenomination_50(), atm.getAtmAmount(), atm.getId() });

			logger.info("updated ===>" + updated);

			if (updated == 0) {
				throw new Exception("Unable to update atm details");
			}
		} catch (Exception e) {
			logger.error("Exception while updating atm details : " + e);
			throw e;
		}
		return updated;
	}

	@Override
	public AccountDetails validateAccountDetails(String accountNo, int pin) throws Exception {
		AccountDetails account = fetchAccountDetails(accountNo);
		if (account == null) {
			throw new AtmException("400", errors.getINVALID_ACCOUNT());
		}

		if (account != null) {
			if (pin != account.getPin()) {
				throw new AtmException("400", errors.getINVALID_PIN());
			}
		}
		return account;
	}

	@Transactional
	private int insertTransaction(TransactionDetails tr) {
		int isInserted = 0;
		String query = "insert into transaction_details (transaction_id, transaction_date, account_number, opening_balance, withdrawal, deposit, closing_balance) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			isInserted = jdbcTemplate.update(query, new Object[] { tr.getTransactionId(), tr.getTransactionDate(),
					tr.getAccountNo(), tr.getOpeningBalance(), tr.getWithdrawal(), tr.getDeposit(), tr.getClosingBalance() });
		} catch (Exception e) {
			logger.error("Exception while inserting transaction details : " + e);
			throw e;
		} finally {

		}
		return isInserted;
	}

	private String generateUUID() {
		return UUID.randomUUID().toString();
	}

}
