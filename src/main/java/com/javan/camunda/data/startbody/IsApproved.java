package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class IsApproved {

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private Boolean value;

	public IsApproved(String type, Boolean value){
		this.type = type;
		this.value = value;
	}

	public String getType(){
		return type;
	}

	public Boolean getValue(){
		return value;
	}
}