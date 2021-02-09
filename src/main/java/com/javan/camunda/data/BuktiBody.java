package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;
import com.javan.camunda.data.startbody.VariablesBukti;
import com.javan.camunda.data.startbody.VariablesReview;

public class BuktiBody {

	@SerializedName("variables")
	private VariablesBukti variablesBukti;


	public BuktiBody(VariablesBukti variablesBukti){
		this.variablesBukti = variablesBukti;
	}
}