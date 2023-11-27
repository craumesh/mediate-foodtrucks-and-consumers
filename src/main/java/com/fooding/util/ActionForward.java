package com.fooding.util;

public class ActionForward {
	// 멤버 변수 선언
	private String path;
	private boolean isRedirect;
	
	// 기본 생성자 정의
	public ActionForward() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메서드 정의
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	// 오버라이딩
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}
	
	

	
	

}
