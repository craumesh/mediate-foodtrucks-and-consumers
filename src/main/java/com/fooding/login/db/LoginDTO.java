package com.fooding.login.db;

public class LoginDTO {
	// 멤버 변수 선언
	private String id;
	private String pw;
	
	// 기본 생성자 정의
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메서드 정의
	public String getId() {
		return id;
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
	
	
}
