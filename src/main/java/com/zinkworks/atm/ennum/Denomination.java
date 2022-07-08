package com.zinkworks.atm.ennum;

/**
 * Denomination enum (DENOMINATION_5, DENOMINATION_10, DENOMINATION_20, DENOMINATION_50).
 * 
 * @author Jagadish Anala
 **/
public enum Denomination {

	DENOMINATION_5(5), DENOMINATION_10(10), DENOMINATION_20(20), DENOMINATION_50(50);

	private int val;

	Denomination(int val) {
		this.val = val;
	}

	public int value() {
		return val;
	}
}
