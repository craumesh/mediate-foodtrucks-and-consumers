package com.fooding.payment.db;

public class StopDateDTO {
	// 정차지(stop) 테이블 
		private int stop_id;
		private String beginaddress; //정착지 주소
		private java.sql.Timestamp begintime;
		private java.sql.Timestamp endtime;
		public int getStop_id() {
			return stop_id;
		}
		public void setStop_id(int stop_id) {
			this.stop_id = stop_id;
		}
		public String getBeginaddress() {
			return beginaddress;
		}
		public void setBeginaddress(String beginaddress) {
			this.beginaddress = beginaddress;
		}
		public java.sql.Timestamp getBegintime() {
			return begintime;
		}
		public void setBegintime(java.sql.Timestamp begintime) {
			this.begintime = begintime;
		}
		public java.sql.Timestamp getEndtime() {
			return endtime;
		}
		public void setEndtime(java.sql.Timestamp endtime) {
			this.endtime = endtime;
		}
		@Override
		public String toString() {
			return "StopDTO [stop_id=" + stop_id + ", beginaddress=" + beginaddress + ", begintime=" + begintime
					+ ", endtime=" + endtime + "]";
		}
		
}
