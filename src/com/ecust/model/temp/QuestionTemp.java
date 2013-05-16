package com.ecust.model.temp;

import java.util.Date;

public class QuestionTemp{
	
	private long q_id;
	
	private long chapter_id;
	
	private int chapter_order;
	
	private String chapter_name;
	
	private short q_hard;
	
	private short q_type;//类型：1练习题、2测试题
	
	private short q_sub_type;
	
	private String q_content;
	
	private String q_tip;
	
	private Date q_date;
	
	private int q_right_count;
	
	private int q_error_count;
	
	public long getQ_id() {
		return q_id;
	}

	public void setQ_id(long q_id) {
		this.q_id = q_id;
	}

	public int getChapter_order() {
		return chapter_order;
	}

	public void setChapter_order(int chapter_order) {
		this.chapter_order = chapter_order;
	}

	public String getChapter_name() {
		return chapter_name;
	}

	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}

	public short getQ_hard() {
		return q_hard;
	}

	public void setQ_hard(short q_hard) {
		this.q_hard = q_hard;
	}

	public short getQ_type() {
		return q_type;
	}

	public void setQ_type(short q_type) {
		this.q_type = q_type;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public String getQ_tip() {
		return q_tip;
	}

	public void setQ_tip(String q_tip) {
		this.q_tip = q_tip;
	}

	public Date getQ_date() {
		return q_date;
	}

	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}

	public long getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(long chapter_id) {
		this.chapter_id = chapter_id;
	}

	public int getQ_right_count() {
		return q_right_count;
	}

	public void setQ_right_count(int q_right_count) {
		this.q_right_count = q_right_count;
	}

	public int getQ_error_count() {
		return q_error_count;
	}

	public void setQ_error_count(int q_error_count) {
		this.q_error_count = q_error_count;
	}

	public short getQ_sub_type() {
		return q_sub_type;
	}

	public void setQ_sub_type(short q_sub_type) {
		this.q_sub_type = q_sub_type;
	}

	public QuestionTemp(long q_id, long chapter_id, int chapter_order,
			String chapter_name, short q_hard, short q_type, short q_sub_type,
			String q_content, String q_tip, Date q_date, int q_right_count,
			int q_error_count) {
		super();
		this.q_id = q_id;
		this.chapter_id = chapter_id;
		this.chapter_order = chapter_order;
		this.chapter_name = chapter_name;
		this.q_hard = q_hard;
		this.q_type = q_type;
		this.q_sub_type = q_sub_type;
		this.q_content = q_content;
		this.q_tip = q_tip;
		this.q_date = q_date;
		this.q_right_count = q_right_count;
		this.q_error_count = q_error_count;
	}

	public QuestionTemp() {
		super();
	}
}
