package com.fooding.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fooding.profile.purchase.db.ProfilePurchaseDTO;

public class AdminDAO {

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

	// 신규 푸드트럭 입력 메서드 (adminFoodtruckAdd() : 매개변수 DTO, 리턴타입 없음)
	public void adminFoodtruckAdd(AdminDTO dto) {

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "INSERT INTO foodtruck (name, info, image, member_id) " + "VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setString(1, dto.getFoodtruck_name());
			pstmt.setString(2, dto.getInfo());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, dto.getMember_id());

			// 4. SQL 구문 실행
			pstmt.executeUpdate();

			System.out.println("\t\t DAO : adminFoodtruckAdd() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 신규 푸드트럭 입력 메서드 (adminFoodtruckAdd() : 매개변수 DTO, 리턴타입 없음)

	// 푸드트럭 목록 개수 세는 메서드 (adminFoodtruckCount() : 매개변수 X, 리턴타입 int)
	public int adminFoodtruckCount() {
		int count = 0;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM foodtruck";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("\t\t DAO : adminFoodtruckCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	}
	// 푸드트럭 목록 개수 세는 메서드 (adminFoodtruckCount() : 매개변수 X, 리턴타입 int)

	// 푸드트럭 목록 받아오는 메서드 (adminFoodtruckList() : 매개변수 X, 리턴타입 ArrayList)
	public ArrayList adminFoodtruckList() {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT * FROM foodtruck ORDER BY foodtruck_id DESC";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setFoodtruck_id(rs.getInt(1));
				dto.setFoodtruck_name(rs.getString(2));
				dto.setInfo(rs.getString(3));
				dto.setImage(rs.getString(4));
				list.add(dto);
			}
			System.out.println("\t\t DAO : adminFoodtruckList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return list;
	}

	// 푸드트럭 목록 받아오는 메서드 (adminFoodtruckList())

	// 푸드트럭 목록 받아오는 메서드 (adminFoodtruckList() : 매개변수 startRow, pageSize, 리턴타입
	// ArrayList)
	public ArrayList adminFoodtruckList(int startRow, int pageSize) {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT * FROM foodtruck ORDER BY foodtruck_id DESC LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setFoodtruck_id(rs.getInt(1));
				dto.setFoodtruck_name(rs.getString(2));
				dto.setInfo(rs.getString(3));
				dto.setImage(rs.getString(4));
				dto.setF_activation(rs.getString("activation"));
				list.add(dto);
			}
			System.out.println("\t\t DAO : adminFoodtruckList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return list;
	}

	// 푸드트럭 목록 받아오는 메서드 (adminFoodtruckList())

	// 회원수 받아오는 메서드 (adminMemberCount() 매개변수 X, 리턴타입 int)
	public int adminMemberCount() {
		int count = 0;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM member";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("\t\t DAO : adminMemberCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	}
	// 회원수 받아오는 메서드 (adminMemberCount() 매개변수 X, 리턴타입 int)

	// 회원 목록 받아오는 메서드 (adminMemberList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
	public ArrayList adminMemberList(int startRow, int pageSize) {
		ArrayList list = new ArrayList();

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT * FROM member ORDER BY member_id DESC LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setMember_id(rs.getInt("member_id"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setActivation(rs.getString("activation"));			
				list.add(dto);
			}
			System.out.println("\t\t DAO : adminMemberList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 회원 목록 받아오는 메서드 (adminMemberList() 매개변수 X, 리턴타입 ArrayList)

	// 상품수 받아오는 메서드 (adminProductCount() 매개변수 X, 리턴타입 int)
	public int adminProductCount() {
		int count = 0;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM product";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("\t\t DAO : adminProductCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	}
	// 상품수 받아오는 메서드 (adminProductCount() 매개변수 X, 리턴타입 int)

	// 상품 목록 받아오는 메서드 (adminProductList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
	public ArrayList adminProductList(int startRow, int pageSize) {
		ArrayList list = new ArrayList();

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT product_id, p.name, price, largeclass, smallclass, f.name, p.activation " + "FROM product p " + "JOIN foodtruck f "
					+ "ON p.foodtruck_id = f.foodtruck_id " + "ORDER BY product_id DESC LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setProduct_name(rs.getString("p.name"));
				dto.setPrice(rs.getInt("price"));
				dto.setLargeclass(rs.getString("largeclass"));
				dto.setSmallclass(rs.getString("smallclass"));
				dto.setFoodtruck_name(rs.getString("f.name"));
				dto.setP_activation(rs.getString("p.activation"));
				list.add(dto);
			}
			System.out.println("\t\t DAO : adminProductList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 상품 목록 받아오는 메서드 (adminProductList() 매개변수 X, 리턴타입 ArrayList)

	// 상품 추가 메서드 (adminProductAdd() 매개변수 DTO, 리턴타입 X)
	public void adminProductAdd(AdminDTO dto) {
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "INSERT INTO product (name, price, info, foodtruck_id, image, largeclass, smallclass) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getInfo());
			pstmt.setInt(4, dto.getFoodtruck_id());
			pstmt.setString(5, dto.getImage());
			pstmt.setString(6, dto.getLargeclass());
			pstmt.setString(7, dto.getSmallclass());

			// 4. SQL 구문 실행
			pstmt.executeUpdate();

			System.out.println("\t\t DAO : adminProductAdd() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 상품 추가 메서드 (adminProductAdd() 매개변수 DTO, 리턴타입 X)

	// 회원 삭제 메서드 (adminMemberDelete() 매개변수 member_id, 리턴타입 int)
	public int adminMemberDelete(int member_id) {
		int result = -1;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE member SET updatedate = NOW(), activation = 'N', reason = '강제 탈퇴' WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, member_id);

			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();

			System.out.println("\t\t DAO : adminMemberDelete() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 회원 삭제 메서드 (adminMemberDelete() 매개변수 member_id, 리턴타입 int)

	// 푸드트럭 정보 메서드 (adminFoodtruckContent() 매개변수 foodtruck_id, 리턴타입 dto)
	public AdminDTO adminFoodtruckContent(int foodtruck_id) {
		AdminDTO dto = null;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT foodtruck_id, f.name, info, image, id, f.activation " + "FROM foodtruck f " + "JOIN member m " + "ON f.member_id = m.member_id "
					+ "WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				dto = new AdminDTO();
				dto.setFoodtruck_id(foodtruck_id);
				dto.setFoodtruck_name(rs.getString("f.name"));
				dto.setInfo(rs.getString("info"));
				dto.setImage(rs.getString("image"));
				dto.setId(rs.getString("id"));
				dto.setF_activation(rs.getString("f.activation"));
			}
			System.out.println("dto : " + dto);
			System.out.println("\t\t DAO : adminFoodtruckContent() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;
	}
	// 푸드트럭 정보 메서드 (adminFoodtruckContent() 매개변수 foodtruck_id, 리턴타입 dto)

	// 상품 삭제 메서드 (adminProductDelete() 매개변수 product_id, 리턴타입 int)
	public int adminProductDelete(int product_id) {
		int result = -1;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE product SET activation = 'N' WHERE product_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, product_id);

			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();

			System.out.println("\t\t DAO : adminProductDelete() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 상품 삭제 메서드 (adminProductDelete() 매개변수 product_id, 리턴타입 int)

	// 회원 아이디에 따른 회원번호 조회 (adminMemberidNum() 매개변수 id, 리턴타입 int)
	public int adminMemberidNum(String id) {
		int member_id = -1;
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
				member_id = rs.getInt(1);
			}
			System.out.println("\t\t DAO : adminMemberidNum() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return member_id;
	}

	// 회원 아이디에 따른 회원번호 조회 (adminMemberidNum() 매개변수 id, 리턴타입 int)

	// 푸드트럭 삭제 메서드 (adminFoodtruckDelete() 매개변수 foodtruck_id, 리턴타입 int)
	public int adminFoodtruckDelete(int foodtruck_id) {
		int result = -1;

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE foodtruck SET activation = 'N' WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);

			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : adminFoodtruckDelete() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 푸드트럭 삭제 메서드 (adminFoodtruckDelete() 매개변수 foodtruck_id, 리턴타입 int)

	// 푸드트럭 경로 동일 날짜 검사 메서드
	public boolean adminFoodtruckWayCheck(int foodtruck_id, String date) {
		boolean result = true;

		try {
			con = getCon();
			sql = "SELECT date " + "FROM stopdate " + "WHERE foodtruck_id = ? " + "AND date = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			System.out.println("\t\t DAO : adminFoodtruckWayCheck() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 푸드트럭 경로 동일 날짜 검사 메서드
	
	// 푸드트럭 경로 정보 메서드
			public ArrayList<String> adminFoodtruckContentWay(int foodtruck_id) {
				ArrayList<String> wayInfoArr = new ArrayList<String>();
				try {
					// 1. 2. DB 연결
					con = getCon();
					
					// 3. SQL 구문 작성 및 pstmt 객체 생성
					sql = "SELECT date, COUNT(date) count "
							+ "FROM stopdate "
							+ "WHERE foodtruck_id = ? "
							+ "GROUP BY date "
							+ "ORDER BY date";
					pstmt = con.prepareStatement(sql);
					
					// ? 정보 추가
					pstmt.setInt(1, foodtruck_id);
					
					// 4. SQL 구문 실행
					rs = pstmt.executeQuery();
					
					// 5. 데이터 처리
					while(rs.next()) {
						String wayInfo;
						wayInfo = rs.getString("date") + ", ";
						wayInfo += rs.getInt("count");
						wayInfoArr.add(wayInfo);
					}
					System.out.println("\t\t DAO : adminFoodtruckContentWay() 호출 성공 ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
				}
				return wayInfoArr;
			}
			// 푸드트럭 경로 정보 메서드

	// 푸드트럭 경로 추가 메서드
	public void adminFoodtruckWayAdd(int foodtruck_id, String date, String address, String fulladdr, String stoptime, String largeclass, String smallclass) {
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "insert into stopdate (foodtruck_id, date, address, fulladdr, stoptime, largeclass, smallclass) " + "values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, date);
			pstmt.setString(3, address);
			pstmt.setString(4, fulladdr);
			pstmt.setString(5, stoptime);
			pstmt.setString(6, largeclass);
			pstmt.setString(7, smallclass);
			pstmt.executeUpdate();
			
			System.out.println("\t\t DAO : adminFoodtruckWayAdd() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 푸드트럭 경로 추가 메서드
	
	// 푸드트럭 경로 제거 메서드
	public void adminFoodtruckWayDel(int foodtruck_id, String date) {			
		try {
			con = getCon();
			sql = "SELECT * "
					+ "FROM stopdate "
					+ "WHERE foodtruck_id = ? "
					+ "AND date = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "DELETE FROM stopdate "
						+ "WHERE foodtruck_id = ? "
						+ "AND date = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, foodtruck_id);
				pstmt.setString(2, date);
				pstmt.executeUpdate();
			}
			System.out.println("\t\t DAO : adminFoodtruckWayDel() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}				
	}
	// 푸드트럭 경로 제거 메서드
	
	// 푸드트럭 경로 조회 메서드
	public ArrayList<String> adminFoodtruckWayCall(int foodtruck_id, String date){
		ArrayList<String> wayInfoArr = new ArrayList<String>();
		
		try {
			con = getCon();
			sql = "SELECT address, fulladdr, stoptime "
					+ "FROM stopdate "
					+ "WHERE foodtruck_id = ? "
					+ "AND date = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String wayInfo;
				wayInfo = rs.getString("address") + ", ";
				wayInfo += rs.getString("fulladdr") + ", ";
				wayInfo += rs.getString("stoptime");
				wayInfoArr.add(wayInfo);
			}
			System.out.println("\t\t DAO : adminFoodtruckWayCall() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}			
		return wayInfoArr;
	}
	// 푸드트럭 경로 조회 메서드
					

	// 전체 주문 목록 메서드 (adminPurchaseList() : 매개변수 String id, startRow, pageSize, 리턴타입
	// ArrayList)
	public ArrayList adminPurchaseList(int startRow, int pageSize) {
		ArrayList arr = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT purchaseid, MIN(id), MIN(p.orderdate), MIN(f.name), SUM((price * quantity)) " + "FROM purchase p " + "JOIN product d "
					+ "ON p.product_id = d.product_id " + "JOIN foodtruck f " + "ON d.foodtruck_id = f.foodtruck_id " + "JOIN member m "
					+ "ON p.member_id = m.member_id " + "GROUP BY purchaseid " + "ORDER BY MIN(detail_id) DESC " + "LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				// 주문 내역 dto 생성
				AdminDTO dto = new AdminDTO();
				dto.setPurchaseid(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setOrderdate(rs.getTimestamp(3));
				dto.setFoodtruck_name(rs.getString(4));
				dto.setPrice(rs.getInt(5));
				arr.add(dto);
			}
			System.out.println("\t\t DAO : adminPurchaseList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return arr;
	}
	// 전체 주문 목록 메서드 (adminPurchaseList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)

	// 전체 주문 목록 개수 확인 메서드 (adminPurchaseCount() : 매개변수 String id, 리턴타입 int)
	public int adminPurchaseCount() {
		int count = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(DISTINCT purchaseid) FROM purchase";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("\t\t DAO : adminPurchaseCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return count;
	}
	// 전체 주문 목록 개수 확인 메서드 (adminPurchaseCount() : 매개변수 String id, 리턴타입 int)

	// 주문 상세 내역 조회 메서드 (adminPurchaseDetail() 매개변수 purchase_id, 리턴타입 ArrayList)
	public ArrayList adminPurchaseDetail(int purchaseid) {
		ArrayList list = new ArrayList();

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT detail_id, purchaseid, p.name, quantity, (quantity * price) price, f.name, address, orderdate, date, SUBSTR(stoptime, 1, 5) 'time1', SUBSTR(stoptime, 8) 'time2',  id "
					+ "FROM purchase d " + "JOIN product p " + "ON d.product_id = p.product_id " + "JOIN foodtruck f " + "ON p.foodtruck_id = f.foodtruck_id "
					+ "JOIN member m " + "ON d.member_id = m.member_id " + "WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, purchaseid);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setDetail_id(rs.getInt("detail_id"));
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
				dto.setId(rs.getString("id"));
				list.add(dto);
			}

			System.out.println("\t\t DAO : adminPurchaseDetail() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 주문 상세 내역 조회 메서드 (adminPurchaseDetail() 매개변수 purchase_id, 리턴타입 ArrayList)

	// 푸드트럭 번호에 해당하는 이미지 파일 조회 (adminFoodtruckImage() 매개변수 foodtruck_id, 리턴타입
	// String)
	public String adminFoodtruckImage(int foodtruck_id) {
		String image = "";

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT image FROM foodtruck WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, foodtruck_id);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				image = rs.getString(1);
			}
			System.out.println("\t\t DAO : adminFoodtruckImage() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return image;
	}
	// 푸드트럭 번호에 해당하는 이미지 파일 조회 (adminFoodtruckImage() 매개변수 foodtruck_id, 리턴타입
	// String)

	// 푸드트럭 정보 수정 메서드 (adminFoodtruckUpdate1() 매개변수 dto, 리턴타입 int)
	public int adminFoodtruckUpdate1(AdminDTO dto) {
		int result = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE foodtruck SET name = ?, info = ?, image = ?, updatedate = NOW() WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setString(1, dto.getFoodtruck_name());
			pstmt.setString(2, dto.getInfo());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, dto.getFoodtruck_id());

			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : adminFoodtruckUpdate1() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 푸드트럭 정보 수정 메서드 (adminFoodtruckUpdate1() 매개변수 foodtruck_id, 리턴타입 int)

	// 푸드트럭 정보 수정 메서드 (adminFoodtruckUpdate2() 매개변수 foodtruck_id, 리턴타입 int)
	public int adminFoodtruckUpdate2(AdminDTO dto) {
		int result = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE foodtruck SET name = ?, info = ?, updatedate = NOW() WHERE foodtruck_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			// ? 정보 추가
			pstmt.setString(1, dto.getFoodtruck_name());
			pstmt.setString(2, dto.getInfo());
			pstmt.setInt(3, dto.getFoodtruck_id());

			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : adminFoodtruckUpdate2() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 푸드트럭 정보 수정 메서드 (adminFoodtruckUpdate2() 매개변수 foodtruck_id, 리턴타입 int)

	// 상품 번호에 해당하는 이미지 파일 조회 (adminProductImage() 매개변수 product_id, 리턴타입 String)
	public String adminProductImage(int product_id) {
		String image = "";

		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT image FROM product WHERE product_id = ?";
			pstmt = con.prepareStatement(sql);

			// ? 정보 추가
			pstmt.setInt(1, product_id);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				image = rs.getString(1);
			}
			System.out.println("\t\t DAO : adminProductImage() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return image;
	}
	// 상품 번호에 해당하는 이미지 파일 조회 (adminProductImage() 매개변수 product_id, 리턴타입 String)

	// 주문 내역별 수령일 제일 늦은 날 찾기 (adminPurchaseDate() : 리턴타입 date)
	public String adminPurchaseDate() {
		String date = null;
		try {
			// 1. 2. DB 연결
			con = getCon();

			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT MAX(CONCAT(date, ' ', SUBSTR(stoptime, 8))) FROM purchase";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				date = rs.getString(1);
			}
			System.out.println("\t\t DAO : adminPurchaseDate() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return date;
	}
	// 주문 내역별 수령일 제일 늦은 날 찾기 (adminPurchaseDate() : 리턴타입 date)
}
