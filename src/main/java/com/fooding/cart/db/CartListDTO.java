package com.fooding.cart.db;

import java.sql.Date;

public class CartListDTO {

	private int member_id; //회원번호
	private int cart_id; // 장바구니 번호
	private Date date; // 운행정보 - 운행일
	private String address; // 정차지
	private int foodtruck_id; // 푸드트럭 아이디
	private String image; // 상품 이미지 경로
	private String name; // 상품이름
    private int price; // 상품가격
    private int quantity ; // 상품수량
    
	public CartListDTO() {
		super();
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartListDTO [member_id=" + member_id + ", cart_id=" + cart_id + ", date=" + date + ", address=" + address + ", foodtruck_id=" + foodtruck_id
				+ ", image=" + image + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}

	
	
    
    
}
