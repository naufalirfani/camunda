package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class Keterangan{

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private String value;

	public Keterangan(String type, String value){
		this.type = type;
		this.value = value;
	}

	public String getType(){
		return type;
	}

	public String getValue(){
		return value;
	}
}