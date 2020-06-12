package com.gzeinnumer.daggerhiltpart2.network.model;

import com.google.gson.annotations.SerializedName;

public class Clouds{

	@SerializedName("all")
	private int all;

	public int getAll(){
		return all;
	}
}