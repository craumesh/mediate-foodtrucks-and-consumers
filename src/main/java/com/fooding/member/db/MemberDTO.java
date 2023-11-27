package com.fooding.member.db;

import java.sql.Timestamp;

/**
 *   MemberDTO 클래스는  (Data Transfer Object) => DB테이블의 정보를 저장하는 객체 
 */

public class MemberDTO {

	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private Timestamp regdate;
	
	public MemberDTO() {
		System.out.println(" 객체 초기화(생성) 완료! ");
		System.out.println(" DB정보를 저장할 준비 완료! ");
	}

	
	// alt shift s + r  
	// => set/get 메서드 자동생성	
	
	
	public String getId() {
		return id;
	}

	public int getMember_id() {
		return member_id;
	}


	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	// alt shift s + s
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", regdate=" + regdate + "]";
	}
	
}
