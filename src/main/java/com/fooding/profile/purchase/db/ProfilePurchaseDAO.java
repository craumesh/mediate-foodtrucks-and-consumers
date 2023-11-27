package com.fooding.profile.purchase.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProfilePurchaseDAO {

	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// DB 연결 메서드 (getCon)
	private Connection getCon() throws Exception {

		// 커넥션 풀 이용
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();
		return con;
	}
	// DB 연결 메서드

	// DB 연결 해제 메서드 (closeDB)
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// DB 연결 해제 메서드

	// 내 주문 목록 개수 확인 메서드 (profilePurchaseCount() : 매개변수 String id, 리턴타입 int)
	public int profilePurchaseCount(String id) {
		int result = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setString(1, id);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				int member_id = rs.getInt("member_id");

				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(DISTINCT purchaseid) FROM purchase WHERE member_id = ?";
				pstmt = con.prepareStatement(sql);

				// ? 정보 추가
				pstmt.setInt(1, member_id);

				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();

				// 5. 데이터 처리
				if (rs.next()) {
					result = rs.getInt(1);
				}
			}
			System.out.println("\t\t DAO : profilePurchaseCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 내 주문 목록 개수 확인 메서드 (profilePurchaseCount() : 매개변수 String id, 리턴타입 int)

	// 내 주문 목록 메서드 (profilePurchaseList() : 매개변수 String id, startRow, pageSize, 리턴타입
	// ArrayList)
	public ArrayList profilePurchaseList(String id, int startRow, int pageSize) {
		ArrayList arr = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setString(1, id);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				int member_id = rs.getInt("member_id");

				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT purchaseid, MIN(p.orderdate), MIN(f.name), SUM((price * quantity)) " + "FROM purchase p "
						+ "JOIN product d " + "ON p.product_id = d.product_id " + "JOIN foodtruck f "
						+ "ON d.foodtruck_id = f.foodtruck_id " + "WHERE p.member_id = ? " + "GROUP BY purchaseid "
						+ "ORDER BY MIN(detail_id) DESC " + "LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);

				// ? 정보 추가
				pstmt.setInt(1, member_id);
				pstmt.setInt(2, startRow - 1);
				pstmt.setInt(3, pageSize);

				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();

				// 5. 데이터 처리
				while (rs.next()) {
					// 주문 내역 dto 생성
					ProfilePurchaseDTO dto = new ProfilePurchaseDTO();
					dto.setPurchaseid(rs.getInt(1));
					dto.setOrderdate(rs.getTimestamp(2));
					dto.setFoodtruck_name(rs.getString(3));
					dto.setPrice(rs.getInt(4));
					arr.add(dto);
				}
			}
			System.out.println("\t\t DAO : profilePurchaseList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return arr;
	}
	// 내 주문 목록 메서드 (profilePurchaseList() 매개변수 String id, startRow, pageSize, 리턴타입
	// ArrayList)

	// 주문 상세 내역 조회 메서드 (profilePurchaseDetail() 매개변수 purchase_id, 리턴타입 ArrayList)
	public ArrayList profilePurchaseDetail(int purchaseid) {
		ArrayList list = new ArrayList();

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT purchaseid, p.name, quantity, (quantity * price) price, f.name, address, orderdate, date, SUBSTR(stoptime, 1, 5) 'time1', SUBSTR(stoptime, 8) 'time2' "
					+ "FROM purchase d " + "JOIN product p " + "ON d.product_id = p.product_id " + "JOIN foodtruck f "
					+ "ON p.foodtruck_id = f.foodtruck_id " + "WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, purchaseid);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				ProfilePurchaseDTO dto = new ProfilePurchaseDTO();
				dto.setPurchaseid(purchaseid);
				dto.setName(rs.getString("p.name"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setPrice(rs.getInt("price"));
				dto.setFoodtruck_name(rs.getString("f.name"));
				dto.setOrderdate(rs.getTimestamp("orderdate"));
				dto.setAddress(rs.getString("address"));
				dto.setDate(rs.getDate("date"));
				dto.setTime1(rs.getString("time1"));
				dto.setTime2(rs.getString("time2"));
				list.add(dto);
			}

			System.out.println("\t\t DAO : profilePurchaseDetail() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 주문 상세 내역 조회 메서드 (profilePurchaseDetail() 매개변수 purchase_id, 리턴타입 ArrayList)

	// 주문 내역별 수령일 제일 늦은 날 찾기 (profilePurchaseDate() : 매개변수 purchaseid, 리턴타입 date)
	public String profilePurchaseDate(int purchaseid) {
		String date = null;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT MAX(CONCAT(date, ' ', SUBSTR(stoptime, 8))) FROM purchase WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, purchaseid);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				date = rs.getString(1);
			}
			System.out.println("\t\t DAO : profilePurchaseDate() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return date;
	}
	// 주문 내역별 수령일 제일 늦은 날 찾기 (profilePurchaseDate() : 매개변수 purchaseid, 리턴타입 date)
}
