package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;
import com.javan.camunda.data.startbody.VariablesPenolakan;
import com.javan.camunda.data.startbody.VariablesReview;

public class PenolakanBody {

	@SerializedName("variables")
	private VariablesPenolakan variablesPenolakan;


	public PenolakanBody(VariablesPenolakan variablesPenolakan){
		this.variablesPenolakan = variablesPenolakan;
	}
}