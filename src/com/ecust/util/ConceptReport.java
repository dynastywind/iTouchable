package com.ecust.util;

import java.util.LinkedList;
import java.util.List;

public class ConceptReport {
	private String name;
	private List<Float> whole;//weight value to decide this concept's master value as a whole
	private List<Float> weight;//this concept's weight in a particular question
	private List<Float> confidence;
	public ConceptReport(String name){
		whole=new LinkedList<Float>();
		weight=new LinkedList<Float>();
		confidence=new LinkedList<Float>();
	}
	public String getName() {
		return name;
	}
	public List<Float> getWhole() {
		return whole;
	}
	public List<Float> getWeight() {
		return weight;
	}
	public List<Float> getConfidence() {
		return confidence;
	}
}
