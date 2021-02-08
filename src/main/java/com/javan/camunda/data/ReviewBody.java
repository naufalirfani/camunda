package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;
import com.javan.camunda.data.startbody.VariablesReview;

public class ReviewBody {

	@SerializedName("variables")
	private VariablesReview variablesReview;


	public ReviewBody(VariablesReview variablesReview){
		this.variablesReview = variablesReview;
	}
}