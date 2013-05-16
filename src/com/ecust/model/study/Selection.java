package com.ecust.model.study;

import java.util.List;

public class Selection extends Question{
	private static final long serialVersionUID = -5823240722993012613L;
	
	private List<Option> options;
	
	private Character key;
	
	public Selection(){
		
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public Character getKey() {
		return key;
	}
	public void setKey(Character key) {
		this.key = key;
	}
}
