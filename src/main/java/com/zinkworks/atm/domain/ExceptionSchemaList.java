package com.zinkworks.atm.domain;

import java.util.List;

public class ExceptionSchemaList {

	public List<ExceptionSchema> myArray;

	public ExceptionSchemaList() {
		super();
	}

	public ExceptionSchemaList(List<ExceptionSchema> myArray) {
		super();
		this.myArray = myArray;
	}

	public List<ExceptionSchema> getMyArray() {
		return myArray;
	}

	public void setMyArray(List<ExceptionSchema> myArray) {
		this.myArray = myArray;
	}

}
