package com.javan.camunda.data.taskVariable;

import com.google.gson.annotations.SerializedName;

public class TaskVariableResponse{

	@SerializedName("keterangan")
	private Keterangan keterangan;

	@SerializedName("jumlah")
	private Jumlah jumlah;

	@SerializedName("name")
	private Name name;

	public Keterangan getKeterangan(){
		return keterangan;
	}

	public Jumlah getJumlah(){
		return jumlah;
	}

	public Name getName(){
		return name;
	}
}