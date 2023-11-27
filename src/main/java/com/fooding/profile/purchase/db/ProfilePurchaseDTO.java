package com.fooding.profile.purchase.db;

import java.sql.Date;
import java.sql.Timestamp;

public class ProfilePurchaseDTO {
	// 멤버 변수 선언
	// purchase 테이블
	private int detail_id;
	private int purchaseid;
	private int member_id;
	private int product_id;
	private int quantity;
	private Timestamp orderdate;
	private Date date;
	private String address;
	private String stoptime;
	
	private String time1;
	private String time2;
	
	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	// member 테이블
	private String id;

	// product 테이블
	private String name;
	private int price;
	private String info;
	private int foodtruck_id;
	private String image;
	private String largeclass;
	private String smallclass;
	
	// foodtruck 테이블
	private String foodtruck_name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	
	// 기본 생성자 정의
	public ProfilePurchaseDTO() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메서드 정의
	public int getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getFoodtruck_id() {
		return foodtruck_id;
	}

	public void setFoodtruck_id(int foodtruck_id) {
		this.foodtruck_id = foodtruck_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLargeclass() {
		return largeclass;
	}

	public void setLargeclass(String largeclass) {
		this.largeclass = largeclass;
	}

	public String getSmallclass() {
		return smallclass;
	}

	public void setSmallclass(String smallclass) {
		this.smallclass = smallclass;
	}

	public String getFoodtruck_name() {
		return foodtruck_name;
	}

	public void setFoodtruck_name(String foodtruck_name) {
		this.foodtruck_name = foodtruck_name;
	}

	// toString() 오버라이딩
	@Override
	public String toString() {
		return "ProfilePurchaseDTO [detail_id=" + detail_id + ", purchaseid=" + purchaseid + ", member_id=" + member_id
				+ ", product_id=" + product_id + ", quantity=" + quantity + ", address=" + address + ", date=" + date
				+ ", name=" + name + ", price=" + price + ", info=" + info + ", foodtruck_id=" + foodtruck_id
				+ ", image=" + image + ", largeclass=" + largeclass + ", smallclass=" + smallclass + ", foodtruck_name="
				+ foodtruck_name + "]";
	}

	
	

	
	



	
	
	

}
