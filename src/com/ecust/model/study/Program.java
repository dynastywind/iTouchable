package com.ecust.model.study;


public class Program extends Question{
	private static final long serialVersionUID = -5889969680037518319L;
	
	private String mainToUser;	//主函数
	
	private String mainToTest;	//测试主函数
	
	private String other;	//辅助函数
	
	private String key;
	
	private String keyToTest;	//测试答案
	
	public Program(){
		
	}
	public String getMainToUser() {
		return mainToUser;
	}
	public void setMainToUser(String mainToUser) {
		this.mainToUser = mainToUser;
	}
	public String getMainToTest() {
		return mainToTest;
	}
	public void setMainToTest(String mainToTest) {
		this.mainToTest = mainToTest;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyToTest() {
		return keyToTest;
	}
	public void setKeyToTest(String keyToTest) {
		this.keyToTest = keyToTest;
	}
}
