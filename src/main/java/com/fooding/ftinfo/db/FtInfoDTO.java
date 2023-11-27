package com.fooding.ftinfo.db;

import java.util.List;

/**
 * 
 * FtInfoDTO : 이것은 푸드트럭 정보 페이지의 DB관련 정보를 저장하는 객체이노라
 * (Data Transfer Object)
 * 
 */

public class FtInfoDTO {
	private int foodtruck_id;
	private String ftname;
	private String ftinfo;
	private String ftimage;
	private int stopdate_id;
	private List<String> date;
	private int stop_id;
	private List<String> address;
	private List<String> time;
	private List<String> pdname;
	private List<String> pdinfo;
	private List<String> pdimage;
	private List<Integer> price;
	
	public FtInfoDTO() {
		System.out.println(" DTO : 객체 초기화 완료!");
	}

	public int getFoodtruck_id() {
		return foodtruck_id;
	}

	public void setFoodtruck_id(int foodtruck_id) {
		this.foodtruck_id = foodtruck_id;
	}

	public String getFtname() {
		return ftname;
	}

	public void setFtname(String ftname) {
		this.ftname = ftname;
	}

	public String getFtinfo() {
		return ftinfo;
	}

	public void setFtinfo(String ftinfo) {
		this.ftinfo = ftinfo;
	}

	public String getFtimage() {
		return ftimage;
	}

	public void setFtimage(String ftimage) {
		this.ftimage = ftimage;
	}

	public int getStopdate_id() {
		return stopdate_id;
	}

	public void setStopdate_id(int stopdate_id) {
		this.stopdate_id = stopdate_id;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public int getStop_id() {
		return stop_id;
	}

	public void setStop_id(int stop_id) {
		this.stop_id = stop_id;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	public List<String> getTime() {
		return time;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}

	public List<String> getPdname() {
		return pdname;
	}

	public void setPdname(List<String> pdname) {
		this.pdname = pdname;
	}

	public List<String> getPdinfo() {
		return pdinfo;
	}

	public void setPdinfo(List<String> pdinfo) {
		this.pdinfo = pdinfo;
	}

	public List<String> getPdimage() {
		return pdimage;
	}

	public void setPdimage(List<String> pdimage) {
		this.pdimage = pdimage;
	}

	public List<Integer> getPrice() {
		return price;
	}

	public void setPrice(List<Integer> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "FtInfoDTO [foodtruck_id=" + foodtruck_id + ", ftname=" + ftname + ", ftinfo=" + ftinfo + ", ftimage=" + ftimage + ", stopdate_id=" + stopdate_id
				+ ", date=" + date + ", stop_id=" + stop_id + ", address=" + address + ", time=" + time + ", pdname=" + pdname + ", pdinfo=" + pdinfo
				+ ", pdimage=" + pdimage + ", price=" + price + "]";
	}

}
