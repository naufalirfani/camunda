package com.javan.camunda.data.taskVariable;

import com.google.gson.annotations.SerializedName;

public class Jumlah{

	@SerializedName("valueInfo")
	private ValueInfo valueInfo;

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private int value;

	public ValueInfo getValueInfo(){
		return valueInfo;
	}

	public String getType(){
		return type;
	}

	public int getValue(){
		return value;
	}
}