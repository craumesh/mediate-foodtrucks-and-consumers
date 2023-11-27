package com.fooding.ft.db;

public class FTDTO {
	
	// 푸드트럭 foodtruck
	private int foodtruck_id; // 트럭번호
	private String ftName; // 상호명 (푸드트럭명)
	private String ftInfo; // 정보
	private String FImage; // 이미지 경로
	
	// 상품 product
	private int product_id; // 상품 번호
	private String pdName; // 상품 이름 (음식명)
	private int price; // 가격
	private String PdInfo; // 정보
//	private int PFoodtruck_id; // 트럭번호
	private String PImage; // 이미지 경로
	private String PLargeclass; // 대분류
	private String PSmallclass; // 소분류
	
	// 운행정보 stopdate
	private int stopdate_id; // 운행 정보 번호
//	private int SFoodtruck_id; // 트럭번호
	private String date; // 운행일
	private String address; // 정차지 (운행 지역)
	private String stoptime; // 정차시간 (운행 시간)
	private String SLargeclass; // 대분류
	private String SSmallclass; // 소분류
	
	public FTDTO() {}

	public int getFoodtruck_id() {
		return foodtruck_id;
	}

	public void setFoodtruck_id(int foodtruck_id) {
		this.foodtruck_id = foodtruck_id;
	}

	public String getFtName() {
		return ftName;
	}

	public void setFtName(String ftName) {
		this.ftName = ftName;
	}

	public String getFtInfo() {
		return ftInfo;
	}

	public void setFtInfo(String ftInfo) {
		this.ftInfo = ftInfo;
	}

	public String getFImage() {
		return FImage;
	}

	public void setFImage(String fImage) {
		FImage = fImage;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPdInfo() {
		return PdInfo;
	}

	public void setPdInfo(String pdInfo) {
		PdInfo = pdInfo;
	}

	public String getPImage() {
		return PImage;
	}

	public void setPImage(String pImage) {
		PImage = pImage;
	}

	public String getPLargeclass() {
		return PLargeclass;
	}

	public void setPLargeclass(String pLargeclass) {
		PLargeclass = pLargeclass;
	}

	public String getPSmallclass() {
		return PSmallclass;
	}

	public void setPSmallclass(String pSmallclass) {
		PSmallclass = pSmallclass;
	}

	public int getStopdate_id() {
		return stopdate_id;
	}

	public void setStopdate_id(int stopdate_id) {
		this.stopdate_id = stopdate_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getSLargeclass() {
		return SLargeclass;
	}

	public void setSLargeclass(String sLargeclass) {
		SLargeclass = sLargeclass;
	}

	public String getSSmallclass() {
		return SSmallclass;
	}

	public void setSSmallclass(String sSmallclass) {
		SSmallclass = sSmallclass;
	}

	@Override
	public String toString() {
		return "FTDTO [foodtruck_id=" + foodtruck_id + ", ftName=" + ftName + ", ftInfo=" + ftInfo + ", FImage="
				+ FImage + ", product_id=" + product_id + ", pdName=" + pdName + ", price=" + price + ", PdInfo="
				+ PdInfo + ", PImage=" + PImage + ", PLargeclass=" + PLargeclass + ", PSmallclass=" + PSmallclass
				+ ", stopdate_id=" + stopdate_id + ", date=" + date + ", address=" + address + ", stoptime=" + stoptime
				+ ", SLargeclass=" + SLargeclass + ", SSmallclass=" + SSmallclass + "]";
	}
	
}
