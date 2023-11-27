package com.fooding.profile.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fooding.admin.db.AdminDTO;

public class ProfileDAO {
	
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
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// DB 연결 해제 메서드
	
	// 프로필 정보 받아오는 메서드 (profileInfo() : 리턴타입 dto, 매개변수 id)
	public ProfileDTO profileInfo(String id) {
		ProfileDTO dto = null;
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성 (SELECT)
			sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				dto = new ProfileDTO();
				dto.setMember_id(rs.getInt("member_id"));
				dto.setId(id);
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
			}
			System.out.println("\t\t DAO : profileInfo() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	// 프로필 정보 받아오는 메서드
	
	// 회원 정보 수정 메서드 (profileUpdate() : 매개변수 dto, 리턴타입 int)
	public int profileUpdate(ProfileDTO dto) {
		int result = -1;
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE member SET pw = ?, name = ?, phone = ?, email = ?, updatedate = NOW() WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getId());
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			
			System.out.println("\t\t DAO : profileUpdate() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	// 회원 정보 수정 메서드
	
	// 회원 탈퇴 메서드 (profileDelete() : 매개변수 X, 리턴타입 int)
	public int profileDelete(ProfileDTO dto) {
		int result = -1;
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "DELETE FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, dto.getId());
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			
			System.out.println("\t\t DAO : profileDelete() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 회원 탈퇴 메서드
	
	// 멤버 id로 푸드트럭 개수 확인 (profileFoodtruckCount() 매개변수 member_id, 리턴타입 int)
	public int profileFoodtruckCount(int member_id) {
		int count = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM foodtruck WHERE member_id = ? AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				count = rs.getInt(1);	
			}
			System.out.println("\t\t DAO : profileFoodtruck() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return count;
	}
	// 멤버 id로 푸드 트럭 개수 확인 (profileFoodtruckCount() 매개변수 member_id, 리턴타입 int)
	
	// id로 member_id 조회 (profileMember_id() 매개변수 id, 리턴타입 int)
	public int profileMember_id(String id) {
		int member_id = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				member_id = rs.getInt(1);
			}
			System.out.println("\t\t DAO : profileMember_id() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return member_id;
	}
	// id로 member_id 조회 (profileMember_id() 매개변수 id, 리턴타입 int)
	
	// member_id로 푸드트럭 리스트 조회 (profileFoodtruckList() 매개변수 member_id, 리턴타입 DTO)
	public ArrayList profileFoodtruckList(int member_id) {
		ArrayList list = new ArrayList();
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT * FROM foodtruck WHERE member_id = ? AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 작성 및 pstmt 객체 생성
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setFoodtruck_id(rs.getInt("foodtruck_id"));
				dto.setFoodtruck_name(rs.getString("name"));
				dto.setInfo(rs.getString("info"));
				dto.setImage(rs.getString("image"));
				dto.setActivation(rs.getString("activation"));
				dto.setMember_id(member_id);
				list.add(dto);
			}
			System.out.println("\t\t DAO : profileFoodtruckList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return list;
	}
	
	// member_id로 푸드트럭 정보 조회 (profileFoodtruckList() 매개변수 member_id, 리턴타입 DTO)
	
	// member_id로 푸드트럭 리스트 조회 (profileFoodtruckList() 매개변수 member_id, 리턴타입 DTO)
		public ArrayList profileFoodtruckList(int member_id, int startRow, int pageSize) {
			ArrayList list = new ArrayList();
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT * FROM foodtruck WHERE member_id = ? AND activation = 'Y' ORDER BY foodtruck_id DESC "
						+ "LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, member_id);
				pstmt.setInt(2, startRow - 1);
				pstmt.setInt(3, pageSize);
				
				// 4. SQL 구문 작성 및 pstmt 객체 생성
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					AdminDTO dto = new AdminDTO();
					dto.setFoodtruck_id(rs.getInt("foodtruck_id"));
					dto.setFoodtruck_name(rs.getString("name"));
					dto.setInfo(rs.getString("info"));
					dto.setImage(rs.getString("image"));
					dto.setActivation(rs.getString("activation"));
					dto.setMember_id(member_id);
					list.add(dto);
				}
				System.out.println("\t\t DAO : profileFoodtruckList() 호출 성공 ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return list;
		}
		
		// member_id로 푸드트럭 정보 조회 (profileFoodtruckInfo() 매개변수 member_id, 리턴타입 DTO)
	
	// 상품 목록 받아오는 메서드 (profileProductList() 매개변수 member_id, startRow, pageSize, 리턴타입 ArrayList)
	public ArrayList profileProductList(int member_id, int startRow, int pageSize) {
		ArrayList list = new ArrayList();
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT product_id, p.name, price, largeclass, smallclass, f.name "
					+ "FROM product p "
					+ "JOIN foodtruck f "
					+ "ON p.foodtruck_id = f.foodtruck_id "
					+ "WHERE member_id = ? AND p.activation = 'Y' "
					+ "ORDER BY product_id DESC LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				ProfileDTO dto = new ProfileDTO();
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setProduct_name(rs.getString("p.name"));
				dto.setPrice(rs.getInt("price"));
				dto.setLargeclass(rs.getString("largeclass"));
				dto.setSmallclass(rs.getString("smallclass"));
				dto.setFoodtruck_name(rs.getString("f.name"));
				list.add(dto);
			}
			System.out.println("\t\t DAO : ProfileProductList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 상품 목록 받아오는 메서드 (profileProductList() 매개변수 member_id, startRow, pageSize, 리턴타입 ArrayList)
	
	// 상품수 받아오는 메서드 (profileProductCount() 매개변수 member_id, 리턴타입 int)
	public int profileProductCount(int member_id) {
		int count = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) "
					+ "FROM product "
					+ "WHERE foodtruck_id IN "
					+ "(SELECT foodtruck_id FROM foodtruck WHERE member_id = ?)";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("\t\t DAO : ProfileProductCount() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return count;
	}
	// 상품수 받아오는 메서드 (profileProductCount() 매개변수 member_id, 리턴타입 int)
	
	// 상품 추가 메서드 (ProfileProductAdd() 매개변수 DTO, 리턴타입 X)
	public void profileProductAdd(ProfileDTO dto) {
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "INSERT INTO product (name, price, info, foodtruck_id, image, largeclass, smallclass) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
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
		
			System.out.println("\t\t DAO : ProfileProductAdd() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 상품 추가 메서드 (profileProductAdd() 매개변수 DTO, 리턴타입 X)
	
	// 상품 삭제 메서드 (profileProductDelete() 매개변수 product_id, 리턴타입 int)
	public int profileProductDelete(int product_id) {
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
			
			System.out.println("\t\t DAO : profileProductDelete() 호출 성공 ");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 상품 삭제 메서드 (profileProductDelete() 매개변수 product_id, 리턴타입 int)
	
	// 상품 번호에 해당하는 이미지 파일 조회 (profileProductImage() 매개변수 product_id, 리턴타입 String)
	public String profileProductImage(int product_id) {
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
			if(rs.next()) {
				image = rs.getString(1);
			}
			System.out.println("\t\t DAO : profileProductImage() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return image;
	}
	// 상품 번호에 해당하는 이미지 파일 조회 (profileProductImage() 매개변수 product_id, 리턴타입 String)
	
	// 중복 전화번호 확인 (profilePhoneDuplicate() 매개변수 member_id, phone, 리턴타입 int)
	public int profilePhoneDuplicate(int member_id, String phone) {
		int result = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM member WHERE member_id != ? AND phone = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			pstmt.setString(2, phone);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("\t\t DAO : profilePhoneDuplicate() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 중복 전화번호 확인 (profilePhoneDuplicate() 매개변수 member_id, phone, 리턴타입 int)
	
	// 중복 이메일 확인 (profileEmailDuplicate() 매개변수 member_id, email, 리턴타입 int)
	public int profileEmailDuplicate(int member_id, String email) {
		int result = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT COUNT(*) FROM member WHERE member_id != ? AND email = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			pstmt.setString(2, email);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("\t\t DAO : profileEmailDuplicate() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 중복 이메일 확인 (profileEmailDuplicate() 매개변수 member_id, email, 리턴타입 int)
	
	// 현재 자신의 휴대전화 번호 확인 (profileMyPhone() 매개변수 member_id, 리턴타입 String)
	public String profileMyPhone(int member_id) {
		String myPhone = "";
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT phone FROM member WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				myPhone = rs.getString(1);
			}
			System.out.println("\t\t DAO : profileMyPhone() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return myPhone;
	}
	// 현재 자신의 휴대전화 번호 확인 (profileMyPhone() 매개변수 member_id, 리턴타입 String)
	
	// 현재 자신의 이메일 확인 (profileMyEmail() 매개변수 member_id, 리턴타입 String)
	public String profileMyEmail(int member_id) {
		String myEmail = "";
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT email FROM member WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				myEmail = rs.getString(1);
			}
			System.out.println("\t\t DAO : profileMyEmail() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return myEmail;
	}
	// 현재 자신의 이메일 확인 (profileMyEmail() 매개변수 member_id, 리턴타입 String)
	
	// 정보 남기는 회원 탈퇴 메서드 (profileChangeActivation() 매개변수 member_id, 리턴타입 int)
	public int profileChangeActivation(int member_id, ProfileDTO dto) {
		int result = 0;
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE member SET updatedate = NOW(), activation = 'N', reason = ? WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, dto.getReason());
			pstmt.setInt(2, member_id);
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : profileChangeActivation() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 정보 남기는 회원 탈퇴 메서드 (profileChangeActivation() 매개변수 member_id, 리턴타입 int)
	
	// 비밀번호 조회 메서드 (profilePassword() 매개변수 member_id, 리턴타입 String)
	public String profilePassword(int member_id) {
		String pw = "";
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT pw FROM member WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				pw = rs.getString(1);
			}
			System.out.println("\t\t DAO : profilePassword() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return pw;
	}
	// 비밀번호 조회 메서드 (profilePassword() 매개변수 member_id, 리턴타입 String)
	
	// 내 푸드트럭의 주문 개수 조회 메서드 (profileFoodtruckPurchaseCount() 매개변수 member_id, 리턴타입 int)
		public int profileFoodtruckPurchaseCount(int member_id) {
			int count = 0;
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) "
						+ "FROM purchase pc "
						+ "JOIN member m "
						+ "ON pc.member_id = m.member_id "
						+ "JOIN product p "
						+ "ON pc.product_id = p.product_id "
						+ "JOIN foodtruck f "
						+ "ON p.foodtruck_id = f.foodtruck_id "
						+ "WHERE f.member_id = ? "
						+ "AND date >= CURRENT_DATE()";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, member_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
				System.out.println("\t\t DAO : profileFoodtruckPurchaseCount() 호출 성공 ");					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return count;
		}
		// 내 푸드트럭의 주문 개수 조회 메서드 (profileFoodtruckPurchaseList() 매개변수 member_id, 리턴타입 ArrayList)
	
	// 내 푸드트럭의 주문 조회 메서드 (profileFoodtruckPurchaseList() 매개변수 member_id, 리턴타입 ArrayList)
	public ArrayList profileFoodtruckPurchaseList(int member_id, int startRow, int pageSize) {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT detail_id, purchaseid, id, p.name, "
					+ "quantity, (price * quantity) AS 'sum', "
					+ "address, CONCAT(date, ' ', SUBSTR(stoptime, 1, 5), ' ~ ', SUBSTR(stoptime, 8)) AS 'pickup', f.name "
					+ "FROM purchase pc "
					+ "JOIN member m "
					+ "ON pc.member_id = m.member_id "
					+ "JOIN product p "
					+ "ON pc.product_id = p.product_id "
					+ "JOIN foodtruck f "
					+ "ON p.foodtruck_id = f.foodtruck_id "
					+ "WHERE f.member_id = ? "
					+ "AND date >= CURRENT_DATE() "
					+ "ORDER BY detail_id DESC "
					+ "LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				ProfileDTO dto = new ProfileDTO();
				dto.setDetail_id(rs.getInt(1));
				dto.setPurchaseid(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setProduct_name(rs.getString(4));
				dto.setQuantity(rs.getInt(5));
				dto.setPrice(rs.getInt(6));
				dto.setAddress(rs.getString(7));
				dto.setPickup(rs.getString(8));
				dto.setFoodtruck_name(rs.getString(9));
				list.add(dto);
			}
			System.out.println("\t\t DAO : profileFoodtruckPurchaseList() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 내 푸드트럭의 주문 조회 메서드 (profileFoodtruckPurchaseList() 매개변수 member_id, 리턴타입 ArrayList)
	
}
