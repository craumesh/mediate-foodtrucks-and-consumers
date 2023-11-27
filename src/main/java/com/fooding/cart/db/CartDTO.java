package com.fooding.cart.db;

import java.sql.Date;

public class CartDTO {

	private int cart_id; //장바구니 번호
	private int member_id; //회원번호
	private int product_id; // 수량
	private int quantity; // 운행일
	private Date date; //운행일
	private String address; //정차지
	private String stoptime; //정차시간
	
	
	public CartDTO() {
		super();
		
	}




	public int getCart_id() {
		return cart_id;
	}




	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getStoptime() {
		return stoptime;
	}




	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}




	@Override
	public String toString() {
		return "CartDTO [cart_id=" + cart_id + ", member_id=" + member_id + ", product_id=" + product_id + ", quantity="
				+ quantity + ", date=" + date + ", adderss=" + address + ", stoptime=" + stoptime + "]";
	}
	
	
	
	
	
	
   	
	
	
	
}
