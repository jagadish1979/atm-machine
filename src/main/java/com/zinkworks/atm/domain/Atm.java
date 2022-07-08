package com.zinkworks.atm.domain;

import java.util.HashMap;
import java.util.Map;

public class Atm {

	private int atmAmount;

	private Map<Integer, Integer> denominations = new HashMap<>();

	/**
	 * @return the atmAmount
	 */
	public int getAtmAmount() {
		return atmAmount;
	}

	/**
	 * @param atmAmount the atmAmount to set
	 */
	public void setAtmAmount(int atmAmount) {
		this.atmAmount = atmAmount;
	}

	/**
	 * @return the denominations
	 */
	public Map<Integer, Integer> getDenominations() {
		return denominations;
	}

	/**
	 * @param denominations the denominations to set
	 */
	public void setDenominations(Map<Integer, Integer> denominations) {
		this.denominations = denominations;
	}

	@Override
	public String toString() {
		return "Atm [atmAmount=" + atmAmount + ", denominations=" + denominations + "]";
	}

}
