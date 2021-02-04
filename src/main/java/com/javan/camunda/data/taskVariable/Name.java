package com.javan.camunda.data.taskVariable;

import com.google.gson.annotations.SerializedName;

public class Name{

	@SerializedName("valueInfo")
	private ValueInfo valueInfo;

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private String value;

	public ValueInfo getValueInfo(){
		return valueInfo;
	}

	public String getType(){
		return type;
	}

	public String getValue(){
		return value;
	}
}