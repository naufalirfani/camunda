package com.javan.camunda.data.startbody;

import com.google.gson.annotations.SerializedName;

public class VariablesPenolakan {

	@SerializedName("keterangan")
	private Keterangan keterangan;

	public VariablesPenolakan(Keterangan keterangan) {
		this.keterangan = keterangan;
	}
}