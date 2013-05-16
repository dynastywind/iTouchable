package com.ecust.util;

public class GenerationRequest {
	private String id;//学号
	private GenerationChoice choice;
	private String courseNo;
	private short chapter;
	private int selection;
	private int fill;
	private int program;
	public GenerationRequest(){
		
	}
	public GenerationRequest(String id, GenerationChoice choice, String courseNo) {
		this.id = id;
		this.choice = choice;
		this.courseNo = courseNo;
	}
	public GenerationRequest(String id, GenerationChoice choice, String courseNo,
			short chapter, int selection, int fill, int program) {
		this.id = id;
		this.choice = choice;
		this.courseNo = courseNo;
		this.chapter = chapter;
		this.selection = selection;
		this.fill = fill;
		this.program = program;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GenerationChoice getChoice() {
		return choice;
	}
	public void setChoice(GenerationChoice choice) {
		this.choice = choice;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public short getChapter() {
		return chapter;
	}
	public void setChapter(short chapter) {
		this.chapter = chapter;
	}
	public int getSelection() {
		return selection;
	}
	public void setSelection(int selection) {
		this.selection = selection;
	}
	public int getFill() {
		return fill;
	}
	public void setFill(int fill) {
		this.fill = fill;
	}
	public int getProgram() {
		return program;
	}
	public void setProgram(int program) {
		this.program = program;
	}
}
