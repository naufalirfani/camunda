package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class StartBody{

	@SerializedName("variables")
	private Variables variables;

	@SerializedName("businessKey")
	private String businessKey;

	public StartBody(Variables variables, String businessKey){
		this.variables = variables;
		this.businessKey = businessKey;
	}

	public Variables getVariables(){
		return variables;
	}

	public String getBusinessKey(){
		return businessKey;
	}
}