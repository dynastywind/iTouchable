package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 公告
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_societynotice")
public class SocietyNotice implements Serializable{
	
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="society_own")
	private Society society_own;
	
	private int writer_type;//1:创建者，2:管理员，3：指导教师
	
	private String writer_name;
	
	private Date createdate;
	
	private String content;
	
	private boolean is_top;//置顶
	
	public SocietyNotice() {
		super();
	}
	
	public SocietyNotice(long id) {
		super();
		this.id = id;
	}
	
	public SocietyNotice(long id, int writer_type, String writer_name,
			Date createdate, String content,boolean is_top) {
		super();
		this.id = id;
		this.writer_type = writer_type;
		this.writer_name = writer_name;
		this.createdate = createdate;
		this.content = content;
		this.is_top = is_top;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Society getSociety_own() {
		return society_own;
	}

	public void setSociety_own(Society society_own) {
		this.society_own = society_own;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWriter_type() {
		return writer_type;
	}

	public void setWriter_type(int writer_type) {
		this.writer_type = writer_type;
	}


	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public boolean isIs_top() {
		return is_top;
	}

	public void setIs_top(boolean is_top) {
		this.is_top = is_top;
	}

}
