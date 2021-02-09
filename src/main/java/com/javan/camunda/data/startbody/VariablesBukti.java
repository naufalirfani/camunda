package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class VariablesBukti {

	@SerializedName("imageUrl")
	private ImageUrl imageUrl;

	public VariablesBukti(ImageUrl imageUrl) {
		this.imageUrl = imageUrl;
	}
}