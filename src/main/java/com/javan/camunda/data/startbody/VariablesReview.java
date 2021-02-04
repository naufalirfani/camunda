package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class VariablesReview {

	@SerializedName("isApproved")
	private IsApproved isApproved;

	public VariablesReview(IsApproved isApproved) {
		this.isApproved = isApproved;
	}
}