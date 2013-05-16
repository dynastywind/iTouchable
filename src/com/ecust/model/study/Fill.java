package com.ecust.model.study;

import java.util.List;

public class Fill extends Question{
	private static final long serialVersionUID = -5910474334925144870L;
	
	private List<String> key;
	
	public Fill(){
	}
	
	public List<String> getKey() {
		return key;
	}
	public void setKey(List<String> key) {
		this.key = key;
	}
}
