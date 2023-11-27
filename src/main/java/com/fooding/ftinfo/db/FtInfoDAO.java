package com.fooding.ftinfo.db;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 
 * FtInfoDAO : 이것은 푸드트럭 정보 페이지의 DB관련 처리를 모두 수행하는 객체이노라
 * (Data Access Object)
 *
 */

public class FtInfoDAO {	
	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 공통) 디비 연결하기(CP)
	private Connection getCon() throws Exception{
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();
		
		System.out.println(" DAO : DB 연결 성공! ");
		System.out.println(" DAO : " + con);
		
		return con;
	}
	
	// 공통) DB 자원해제	
	public void CloseDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FtInfoDTO CallFtInfo(int foodtruck_id) {
		FtInfoDTO dto = null;
		
		try {
			con = getCon();
			sql = "SELECT foodtruck_id, name, info, image "
					+ "FROM foodtruck "
					+ "WHERE foodtruck_id = ? "
					+ "AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new FtInfoDTO();
				dto.setFtname(rs.getString("name"));
				dto.setFtinfo(rs.getString("info"));
				dto.setFtimage(rs.getString("image"));	
			}		
			System.out.println(" DAO : 트럭 데이터 조회 성공");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
		return dto;
	}
	
	public FtInfoDTO CallFtDate(FtInfoDTO dto, int foodtruck_id) {
		List<String> ftDateByFt = new ArrayList<String>();
		try {
			con = getCon();
			sql = "SELECT date "
					+ "FROM stopdate "
					+ "WHERE foodtruck_id = ? "
					+ "AND date > now() "
					+ "GROUP BY date "
					+ "ORDER BY date";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ftDateByFt.add(String.valueOf(rs.getDate("date")));	
			}		
			dto.setDate(ftDateByFt);
			System.out.println(" DAO : 트럭 운행일 조회 성공");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
		return dto;
	}
	
	public FtInfoDTO CallFtProduct(FtInfoDTO dto, int foodtruck_id) {
		List<String> pdNameByFt = new ArrayList<String>();
		List<String> pdInfoByFt = new ArrayList<String>();
		List<String> pdImageByFt = new ArrayList<String>();
		List<Integer> pdPriceByFt = new ArrayList<Integer>();
		
		try {
			con = getCon();
			sql = "SELECT product_id, pd.name name, pd.info info, pd.image image, price "
					+ "FROM foodtruck ft JOIN product pd "
					+ "ON ft.foodtruck_id = pd.foodtruck_id "
					+ "WHERE ft.foodtruck_id = ? "
					+ "AND ft.activation = 'Y' "
					+ "AND pd.activation = 'Y' "
					+ "ORDER BY product_id";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pdNameByFt.add(rs.getString("name"));				
				pdInfoByFt.add(rs.getString("info"));
				pdImageByFt.add(rs.getString("image"));
				pdPriceByFt.add(rs.getInt("price"));
			}
			dto.setPdname(pdNameByFt);
			dto.setPdinfo(pdInfoByFt);
			dto.setPdimage(pdImageByFt);
			dto.setPrice(pdPriceByFt);
			
			System.out.println(" DAO : 트럭 상품 데이터 조회 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}			
		return dto;
	}
	
	public FtInfoDTO CallFtDateInfo(FtInfoDTO dto, int foodtruck_id) {
		List<String> adrByDate = new ArrayList<String>();
		List<String> timeByDate = new ArrayList<String>();
		
		try {
			con = getCon();
			sql = "SELECT date, stopdate_id, address, stoptime "
					+ "FROM foodtruck ft JOIN stopdate sd "
					+ "ON ft.foodtruck_id = sd.foodtruck_id "
					+ "WHERE ft.foodtruck_id = ? "
					+ "AND activation = 'Y' "
					+ "ORDER BY date, stoptime";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				adrByDate.add(rs.getString("address"));				
				timeByDate.add(rs.getString("stoptime"));
			}
			dto.setAddress(adrByDate);
			dto.setTime(timeByDate);
			
			System.out.println(" DAO : 트럭 날짜별 데이터 조회 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}			
		return dto;
	}
	
	public FtInfoDTO CallFtDateInfo(String date, int foodtruck_id) {
		FtInfoDTO dto = new FtInfoDTO();
		List<String> adrByDate = new ArrayList<String>();
		List<String> timeByDate = new ArrayList<String>();
		
		try {
			con = getCon();			
			sql = "SELECT stopdate_id, address, stoptime "
					+ "FROM foodtruck ft JOIN stopdate sd "
					+ "ON ft.foodtruck_id = sd.foodtruck_id "
					+ "WHERE ft.foodtruck_id = ? "
					+ "AND date = ? "
					+ "AND activation = 'Y' "
					+ "ORDER BY stoptime";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				adrByDate.add(rs.getString("address"));				
				timeByDate.add(rs.getString("stoptime"));
			}
			dto.setFoodtruck_id(foodtruck_id);
			dto.setAddress(adrByDate);
			dto.setTime(timeByDate);
			
			System.out.println(" DAO : 트럭 날짜별 데이터 조회 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}			
		return dto;
	}
	
	public int CallProductIndex(int foodtruck_id, String product_name) {
		int product_id = -1;
		
		try {
			con = getCon();			
			sql = "SELECT product_id "
					+ "FROM product "
					+ "WHERE foodtruck_id = ? "
					+ "AND name = ? "
					+ "AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, product_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product_id = rs.getInt("product_id");
			}			
			System.out.println(" DAO : 상품 아이디 조회 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return product_id;
	}
	
	public int CallMemberId(String id) {
		int member_id = -1;
		
		try {
			con = getCon();			
			sql = "SELECT member_id "
					+ "FROM member "
					+ "WHERE id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member_id = rs.getInt("member_id");
			}			
			System.out.println(" DAO : 멤버 아이디 조회 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return member_id;
	}
	
	public void InsertToCart(int member_id, int product_id, int quantity, String date, String place, String time) {
		try {
			con = getCon();			
			sql = "INSERT INTO cart(member_id,product_id,quantity,date,address,stoptime) "
					+ "VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, product_id);
			pstmt.setInt(3, quantity);
			pstmt.setString(4, date);
			pstmt.setString(5, place);
			pstmt.setString(6, time);
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 장바구니 데이터 전달 성공");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	
	public int[] foodtruckHasProduct(int foodtruck_id) {
		int count[] = new int[2];
		
		try {
			con = getCon();
			
			sql = "SELECT COUNT(*) "
					+ "FROM product "
					+ "WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, foodtruck_id);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count[0] = rs.getInt(1);
				sql = "SELECT COUNT(*) "
						+ "FROM stopdate "
						+ "WHERE foodtruck_id = ? "
						+ "AND date > now()";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, foodtruck_id);				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count[1] = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return count;
	}		
}
