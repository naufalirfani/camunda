package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class Variables{

	@SerializedName("keterangan")
	private Keterangan keterangan;

	@SerializedName("jumlah")
	private Jumlah jumlah;

	@SerializedName("name")
	private Name name;

	public Variables(Name name, Jumlah jumlah, Keterangan keterangan) {
		this.name = name;
		this.jumlah = jumlah;
		this.keterangan = keterangan;
	}

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