package com.fooding.payment.db;

public class ProductDTO {
	private int product_id; // 상품번호
	private String name;  //상품이름
	private int price;  //가격
	private String image;  //이미지경로
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "ProductDTO [product_id=" + product_id + ", name=" + name + ", price=" + price + ", image=" + image
				+ "]";
	}
	
}
