package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class ReviewBody {

	@SerializedName("variables")
	private VariablesReview variablesReview;


	public ReviewBody(VariablesReview variablesReview){
		this.variablesReview = variablesReview;
	}
}