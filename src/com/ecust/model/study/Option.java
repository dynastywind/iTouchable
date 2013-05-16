package com.ecust.model.study;

/**
 * 选择题的选项类，从属于选择题本身，不能被单独查询
 * @author Gabriel
 *
 */
public class Option {
	
	private Character label;
	
	private String content;
	
	private float weight;
	
	private Selection selection;
	
	public Option(){
		
	}
	public Option(Character label, String content, float weight,
			Selection selection) {
		super();
		this.label = label;
		this.content = content;
		this.weight = weight;
		this.selection = selection;
	}
	public Character getLabel() {
		return label;
	}
	public void setLabel(Character label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Selection getSelection() {
		return selection;
	}
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
}
