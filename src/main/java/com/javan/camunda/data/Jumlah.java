package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class Jumlah{

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private Integer value;

	public Jumlah(String type, Integer value){
		this.type = type;
		this.value = value;
	}

	public String getType(){
		return type;
	}

	public Integer getValue(){
		return value;
	}
}