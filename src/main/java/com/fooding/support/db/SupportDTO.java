package com.fooding.support.db;

import java.sql.Date;
import java.sql.Timestamp;

public class SupportDTO {
	// 멤버 변수 선언
	private int table_id;
	private String title;
	private String content;
	private Date date;
	private String classify;
	private int bno;
	
	// 기본 생성자 정의
	public SupportDTO() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메서드 정의
	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
}
