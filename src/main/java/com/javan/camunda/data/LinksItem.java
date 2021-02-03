package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class LinksItem{

	@SerializedName("method")
	private String method;

	@SerializedName("rel")
	private String rel;

	@SerializedName("href")
	private String href;

	public String getMethod(){
		return method;
	}

	public String getRel(){
		return rel;
	}

	public String getHref(){
		return href;
	}
}