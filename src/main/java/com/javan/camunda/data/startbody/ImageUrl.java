package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class ImageUrl {

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private String value;

	public ImageUrl(String type, String value){
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