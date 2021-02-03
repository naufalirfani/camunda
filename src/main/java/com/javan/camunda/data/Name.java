package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class Name{

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private String value;

	public Name(String type, String value){
		this.type = type;
		this.value = value;
	}
}