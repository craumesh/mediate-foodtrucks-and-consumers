package com.fooding.payment.db;

import java.sql.Timestamp;

public class PurchaseDTO {

	// 주문내역(purchase) 테이블
	private int detail_id;		// 주문상세번호
	private int purchaseid;	// 주문번호 (결제처리할때 하나 만들어넣기)
	private int product_id;		// 상품번호
	private int member_id;		// 회원번호
	private int quantity;		// 수량
	private Timestamp orderdate; // 주문일자
	private String date;		// 운행일
	private String address;		// 주소
	private String stoptime; 	//정차시간(픽업시간)
	
	public Timestamp getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStoptime() {
		return stoptime;
	}
	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
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
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}
	@Override
	public String toString() {
		return "PurchaseDTO [detail_id=" + detail_id + ", purchaseid=" + purchaseid + ", product_id=" + product_id
				+ ", member_id=" + member_id + ", quantity=" + quantity + ", orderdate=" + orderdate + ", date=" + date
				+ ", address=" + address + ", stoptime=" + stoptime + "]";
	}

	
}
