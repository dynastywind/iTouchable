package com.ecust.model.study;

/**
 * 临时测试实体类，由学生ID号唯一确定，一个学生在一个特定的节点只能有一个该实体在数据库中的持久化实例
 * @author Gabriel
 *
 */
public class TemporaryTest {
	
	private String id;
	
	private String address;
	
	public TemporaryTest(){
		
	}
	public TemporaryTest(String id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
