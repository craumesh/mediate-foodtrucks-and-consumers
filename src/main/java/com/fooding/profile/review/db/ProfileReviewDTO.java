package com.fooding.profile.review.db;

import java.sql.Date;
import java.sql.Timestamp;

public class ProfileReviewDTO {
	
	// 멤버 변수 선언
	private int purchaseid;
	private int product_id; // 상품 번호
	private String name; // 상품명
	
	// member
	private int member_id;
	private String id;
	
	// review
	private int review_id;
	private String nickname;
	private String title;
	private String score;
	private String content;
	private String pw;
	private Date date;
	private String image;

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	// foodtruck 테이블
	private String foodtruck_name;
	
	// purchase 테이블
	private Date pdate;
	
	public Date getPdate() {
		return pdate;
	}


	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}


	// 기본 생성자 정의
	public ProfileReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getFoodtruck_name() {
		return foodtruck_name;
	}

	public void setFoodtruck_name(String foodtruck_name) {
		this.foodtruck_name = foodtruck_name;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


}
