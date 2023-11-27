package com.fooding.admin.db;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminDTO {
	
	// 푸드트럭
	private int foodtruck_id;
	private String foodtruck_name;
	private String info;
	private String image;
	private String f_activation;
	
	public String getF_activation() {
		return f_activation;
	}

	public void setF_activation(String f_activation) {
		this.f_activation = f_activation;
	}

	public String getP_activation() {
		return p_activation;
	}

	public void setP_activation(String p_activation) {
		this.p_activation = p_activation;
	}

	// 멤버
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private Timestamp regdate;
	private String activation;
	
	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	// 상품
	private int product_id;
	private String product_name;
	private int price;
	private String product_info;
	private String largeclass;
	private String smallclass;
	private String p_activation;
	
	// 주문 내역
	private int purchaseid;
	private Timestamp orderdate;
	private int quantity;
	private String address;
	private Date date;
	private String stoptime;
	private String time1;
	private String time2;
	private int detail_id;
	
	public int getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}

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

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProduct_info() {
		return product_info;
	}

	public void setProduct_info(String product_info) {
		this.product_info = product_info;
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

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

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

	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getFoodtruck_id() {
		return foodtruck_id;
	}

	public void setFoodtruck_id(int foodtruck_id) {
		this.foodtruck_id = foodtruck_id;
	}

	public String getFoodtruck_name() {
		return foodtruck_name;
	}

	public void setFoodtruck_name(String foodtuck_name) {
		this.foodtruck_name = foodtuck_name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
